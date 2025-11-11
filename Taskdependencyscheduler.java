// Author: Kris Shar
// Topic: Advanced DSA + Concurrency
// Program: Task Dependency Scheduler
// - Detects cycles in dependency graph
// - Produces a topological order (Kahn's algorithm) if acyclic
// - Executes independent tasks concurrently while respecting dependencies
// - Simulates task durations and logs start/finish times

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Task {
    final String id;
    final int durationSeconds; // simulated execution time

    public Task(String id, int durationSeconds) {
        this.id = id;
        this.durationSeconds = durationSeconds;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " ▶️ Starting task " + id + " ("
                    + durationSeconds + "s)");
            TimeUnit.SECONDS.sleep(durationSeconds);
            System.out.println(Thread.currentThread().getName() + " ✅ Finished task " + id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task " + id + " interrupted.");
        }
    }
}

class DependencyGraph {
    private final Map<String, Task> tasks = new HashMap<>();
    private final Map<String, List<String>> adj = new HashMap<>();
    private final Map<String, Integer> indegree = new HashMap<>();

    public void addTask(Task t) {
        tasks.putIfAbsent(t.id, t);
        adj.putIfAbsent(t.id, new ArrayList<>());
        indegree.putIfAbsent(t.id, 0);
    }

    public void addDependency(String before, String after) {
        // before -> after (after depends on before)
        addTask(new Task(before, 0));
        addTask(new Task(after, 0));
        adj.get(before).add(after);
        indegree.put(after, indegree.getOrDefault(after, 0) + 1);
    }

    // Detect cycle using Kahn: if not all nodes can be processed, cycle exists
    public boolean hasCycle() {
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> indeg = new HashMap<>(indegree);
        for (String node : tasks.keySet()) if (indeg.getOrDefault(node, 0) == 0) q.offer(node);
        int processed = 0;
        while (!q.isEmpty()) {
            String u = q.poll();
            processed++;
            for (String v : adj.getOrDefault(u, Collections.emptyList())) {
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0) q.offer(v);
            }
        }
        return processed != tasks.size();
    }

    // Return topological order using Kahn's algorithm (or empty if cycle)
    public List<String> topologicalOrder() {
        List<String> order = new ArrayList<>();
        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> indeg = new HashMap<>(indegree);
        for (String node : tasks.keySet()) if (indeg.getOrDefault(node, 0) == 0) q.offer(node);
        while (!q.isEmpty()) {
            String u = q.poll();
            order.add(u);
            for (String v : adj.getOrDefault(u, Collections.emptyList())) {
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0) q.offer(v);
            }
        }
        if (order.size() != tasks.size()) return Collections.emptyList();
        return order;
    }

    // Execute tasks concurrently when their dependencies are satisfied
    public void executeConcurrently(int maxParallel) throws InterruptedException {
        if (hasCycle()) {
            System.out.println("⛔ Cycle detected in task dependencies. Cannot schedule tasks.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(maxParallel);
        try {
            Map<String, AtomicInteger> remainingDeps = new ConcurrentHashMap<>();
            Map<String, List<String>> reverseAdj = new HashMap<>(); // for notifying dependents

            // init
            for (String t : tasks.keySet()) {
                remainingDeps.put(t, new AtomicInteger(indegree.getOrDefault(t, 0)));
                for (String v : adj.getOrDefault(t, Collections.emptyList())) {
                    reverseAdj.computeIfAbsent(t, k -> new ArrayList<>());
                    reverseAdj.computeIfAbsent(v, k -> new ArrayList<>());
                    reverseAdj.get(t).add(v);
                }
            }

            CountDownLatch doneLatch = new CountDownLatch(tasks.size());
            // Start tasks which have 0 dependencies
            for (String t : tasks.keySet()) {
                if (remainingDeps.get(t).get() == 0) submitTask(t, executor, remainingDeps, reverseAdj, doneLatch);
            }

            doneLatch.await(); // wait for all tasks to finish
            System.out.println("🎉 All tasks completed.");
        } finally {
            executor.shutdownNow();
        }
    }

    private void submitTask(String id, ExecutorService executor,
                            Map<String, AtomicInteger> remainingDeps,
                            Map<String, List<String>> reverseAdj,
                            CountDownLatch doneLatch) {
        Task task = tasks.get(id);
        executor.submit(() -> {
            try {
                task.run();
            } finally {
                // notify dependents
                for (String dependent : adj.getOrDefault(id, Collections.emptyList())) {
                    int rem = remainingDeps.get(dependent).decrementAndGet();
                    if (rem == 0) {
                        submitTask(dependent, executor, remainingDeps, reverseAdj, doneLatch);
                    }
                }
                doneLatch.countDown();
            }
        });
    }

    // Utility to set task durations (after tasks were added)
    public void setTaskDuration(String id, int seconds) {
        Task old = tasks.get(id);
        if (old != null) tasks.put(id, new Task(id, seconds));
    }
}

public class TaskDependencyScheduler {
    public static void main(String[] args) throws InterruptedException {
        DependencyGraph g = new DependencyGraph();

        // Define tasks (id, durationSeconds)
        g.addTask(new Task("Compile", 3));
        g.addTask(new Task("UnitTest", 4));
        g.addTask(new Task("IntegrationTest", 5));
        g.addTask(new Task("Package", 2));
        g.addTask(new Task("Deploy", 3));
        g.addTask(new Task("Notify", 1));

        // Define dependencies:
        // Compile -> UnitTest -> IntegrationTest -> Package -> Deploy -> Notify
        g.addDependency("Compile", "UnitTest");
        g.addDependency("UnitTest", "IntegrationTest");
        g.addDependency("IntegrationTest", "Package");
        g.addDependency("Package", "Deploy");
        g.addDependency("Deploy", "Notify");

        // Add parallelizable tasks: e.g., Lint and StyleCheck after Compile, before UnitTest
        g.addTask(new Task("Lint", 2));
        g.addTask(new Task("StyleCheck", 1));
        g.addDependency("Compile", "Lint");
        g.addDependency("Compile", "StyleCheck");
        g.addDependency("Lint", "UnitTest");
        g.addDependency("StyleCheck", "UnitTest");

        // Optionally set durations (overwrites)
        g.setTaskDuration("Compile", 2);
        g.setTaskDuration("UnitTest", 3);

        System.out.println("🔎 Topological Order:");
        List<String> order = g.topologicalOrder();
        if (order.isEmpty()) {
            System.out.println("⛔ Cannot produce topological order (cycle detected).");
        } else {
            System.out.println(order);
        }

        System.out.println("\n🏃 Executing tasks with max 3 parallel threads...");
        g.executeConcurrently(3);
    }
}
