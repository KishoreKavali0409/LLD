/*
Proxy pattern is a structural design pattern that provides a surrogate/placeholder object for another object and
control access to the real object.

Key idea:
        Instead of the client interacting directly with the real object, it interacts with a proxy, which acts on behalf
        of the real object.
        The proxy and real object implement the same interface, so the client doesn't need to know whether it is talking
        to the proxy or the real object
 */
import java.util.HashMap;
import java.util.Map;
//Subject
interface VideoDownloader{
    String downloadVideo(String videoUrl);
}
//Real Subject
class RealVideoDownloader implements VideoDownloader{
    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from the url: " + videoUrl);
        return "Video content from" + videoUrl;
    }
}
//Proxy
class CachedVideoDownloader implements VideoDownloader{
    private RealVideoDownloader realVideoDownloader;
    private Map<String, String> cache;
    public CachedVideoDownloader(){
        this.realVideoDownloader = new RealVideoDownloader();
        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoUrl) {
        //cache hit
        if(cache.containsKey(videoUrl)){
            System.out.println("Returning cached video for: " + videoUrl);
            return cache.get(videoUrl);
        }
        //cache miss
        System.out.println("Cache miss downloading ");
        String video = realVideoDownloader.downloadVideo(videoUrl);
        //Store download vide in cache
        cache.put(videoUrl, video);
        return video;
    }
}
public class ProxyPattern {
    static void main(String[] args) {
        VideoDownloader videoDownloader = new CachedVideoDownloader();
        //First request -> cache miss -> Real downloader
        videoDownloader.downloadVideo("pattern_url");
        //second request -> cache hit ->
        videoDownloader.downloadVideo("pattern_url");
    }
}
