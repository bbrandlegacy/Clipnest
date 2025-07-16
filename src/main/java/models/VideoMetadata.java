package models;

public class VideoMetadata {
    private String id;
    private String title;
    private String url;
    private String duration;
    private String uploader;
    private String customTag;

    public VideoMetadata(String id, String title, String url, String duration, String uploader, String customTag) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.uploader = uploader;
        this.customTag = customTag;
    }

    // Getters and toString()
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDuration() {
        return duration;
    }

    public String getUploader() {
        return uploader;
    }

    public String getCustomTag() {
        return customTag;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nTitle: %s\nUploader: %s\nDuration: %s\nURL: %s\nTag: %s\n",
                id, title, uploader, duration, url, customTag);
    }
}
