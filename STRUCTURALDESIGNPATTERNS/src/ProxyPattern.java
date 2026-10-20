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
