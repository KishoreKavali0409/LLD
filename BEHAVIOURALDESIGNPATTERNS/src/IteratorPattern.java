/*
Iterator Pattern is a behavioral design pattern that provides a standard way to traverse elements
of a collection sequentially without exposing the collection's internal structure.
 */

import java.util.*;

class Video{
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

interface PlaylistIterator{
    boolean hasNext();
    Video next();
}

class YoutubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;

    public YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position< videos.size();
    }

    @Override
    public Video next() {
        return hasNext()? videos.get(position++):null;
    }
}

interface Playlist{
    PlaylistIterator createIterator();
}

class YoutubePlaylist implements Playlist{
    private List<Video> videos = new ArrayList<>();
    public void addVideo(Video video){
        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator() {
        return new YoutubePlaylistIterator(videos);
    }
}

public class IteratorPattern {
    static void main(String[] args) {
        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("LLD tutorial"));
        playlist.addVideo(new Video("System design basics"));
        PlaylistIterator iterator = playlist.createIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}

/*
Iterator -> Traverse a collection without knowing how the collection is internally stored
YoutubePlaylist
| -> LLD Tutorial
| -> System Design Basics
| -> Design Patterns

CLient should not know what internally they use
iterator.hasNext()
iterator.next()
 */