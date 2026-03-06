import java.util.*;
class VideoData {
    String videoId;
    String content;
    public VideoData(String videoId, String content) {
        this.videoId = videoId;
        this.content = content;
    }
}

public class MultiLevelCache {
    private final int L1_CAPACITY = 10000;
    private final int L2_CAPACITY = 100000;
    private LinkedHashMap<String, VideoData> L1Cache = new LinkedHashMap<>(L1_CAPACITY, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
            return size() > L1_CAPACITY;
        }
    };
    private Map<String, VideoData> L2Cache = new HashMap<>();
    private Map<String, Integer> L2AccessCount = new HashMap<>();
    private final int L2_PROMOTION_THRESHOLD = 3;
    private Map<String, VideoData> L3Database = new HashMap<>();
    private int L1Hits = 0, L2Hits = 0, L3Hits = 0, totalRequests = 0;
    public void addVideoToDB(String videoId, String content) {
        L3Database.put(videoId, new VideoData(videoId, content));
    }

    public VideoData getVideo(String videoId) {
        totalRequests++;
        long start = System.nanoTime();
        if (L1Cache.containsKey(videoId)) {
            L1Hits++;
            simulateDelay(0.5);
            System.out.println(videoId + " → L1 Cache HIT (0.5ms)");
            return L1Cache.get(videoId);
        }
        if (L2Cache.containsKey(videoId)) {
            L2Hits++;
            simulateDelay(5);
            System.out.println(videoId + " → L1 Cache MISS → L2 Cache HIT (5ms)");
            promoteToL1(videoId);
            return L2Cache.get(videoId);
        }
        if (L3Database.containsKey(videoId)) {
            L3Hits++;
            simulateDelay(150);
            System.out.println(videoId + " → L1 Cache MISS → L2 Cache MISS → L3 Database HIT (150ms)");
            addToL2(videoId);
            return L3Database.get(videoId);
        }
        System.out.println(videoId + " → Video NOT FOUND in any level");
        return null;
    }
    private void promoteToL1(String videoId) {
        VideoData video = L2Cache.get(videoId);
        L1Cache.put(videoId, video);
        L2AccessCount.put(videoId, L2AccessCount.getOrDefault(videoId, 0) + 1);
        if (L2AccessCount.get(videoId) >= L2_PROMOTION_THRESHOLD) {
            L1Cache.put(videoId, video);
            System.out.println("Promoted " + videoId + " to L1");
        }
    }
    private void addToL2(String videoId) {
        if (!L2Cache.containsKey(videoId)) {
            if (L2Cache.size() >= L2_CAPACITY) {
                // Evict random L2 item (could implement LRU for L2 too)
                String keyToRemove = L2Cache.keySet().iterator().next();
                L2Cache.remove(keyToRemove);
                L2AccessCount.remove(keyToRemove);
            }
            L2Cache.put(videoId, L3Database.get(videoId));
            L2AccessCount.put(videoId, 1);
            System.out.println("Added " + videoId + " to L2 (access count: 1)");
        }
    }

    public void getStatistics() {
        double L1HitRate = totalRequests == 0 ? 0 : (L1Hits * 100.0 / totalRequests);
        double L2HitRate = totalRequests == 0 ? 0 : (L2Hits * 100.0 / totalRequests);
        double L3HitRate = totalRequests == 0 ? 0 : (L3Hits * 100.0 / totalRequests);
        double avgTime = (L1Hits * 0.5 + L2Hits * 5 + L3Hits * 150) / totalRequests;
        System.out.printf("\nCache Statistics:\nL1: Hit Rate %.2f%%, Avg Time 0.5ms\n", L1HitRate);
        System.out.printf("L2: Hit Rate %.2f%%, Avg Time 5ms\n", L2HitRate);
        System.out.printf("L3: Hit Rate %.2f%%, Avg Time 150ms\n", L3HitRate);
        System.out.printf("Overall: Hit Rate %.2f%%, Avg Time %.2fms\n", L1HitRate + L2HitRate + L3HitRate, avgTime);
    }
    private void simulateDelay(double ms) {
        try { Thread.sleep((long)(ms)); } catch (InterruptedException e) { }
    }

    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();
        cache.addVideoToDB("video_123", "Video Content 123");
        cache.addVideoToDB("video_999", "Video Content 999");
        cache.getVideo("video_123"); // first request
        cache.getVideo("video_123"); // second request → L1 hit
        cache.getVideo("video_999"); // not in L1/L2 → L3 hit
        cache.getStatistics();
    }
}