import repository.VideoDAO;
import models.VideoMetadata;
import services.MetadataFetcher;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n== Clipnest CLI ==");
            System.out.println("1. Add a new video");
            System.out.println("2. Delete a video");
            System.out.println("3. List All Existing Tags");
            System.out.println("4. List videos by tag");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    //add video
                    case "1": {
                        System.out.print("Enter video URL: ");
                        String url = scanner.nextLine();
                        System.out.print("Enter custom tag: ");
                        String tag = scanner.nextLine();

                        VideoMetadata video = MetadataFetcher.fetch(url, tag);
                        VideoDAO.save(video);
                        System.out.println("✅ Video saved.");
                        break;
                    }
                    //del video
                    case "2": {
                        List<String> tags = VideoDAO.getAllTags();
                        System.out.println("\nExisting Tags:");
                        if (tags.isEmpty()) {
                            System.out.println("No tags found.");
                        } else {
                            for (int i = 0; i < tags.size(); i++) {
                                System.out.println((i + 1) + ". " + tags.get(i));
                            }
                        }

                        System.out.println("Choose a tag to filter videos (number or tag name):");
                        String tagInput = scanner.nextLine().trim();

                        String selectedTag = null;

                        // Check if input is a number
                        try {
                            int index = Integer.parseInt(tagInput);
                            if (index >= 1 && index <= tags.size()) {
                                selectedTag = tags.get(index - 1);
                            } else {
                                System.out.println("Invalid number selection.");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            // Not a number, treat as string
                            selectedTag = tagInput;
                        }

                        List<VideoMetadata> videos = VideoDAO.getVideosByTag(selectedTag);

                        if (videos.isEmpty()) {
                            System.out.println("No videos found for this tag.");
                        } else {
                            for (VideoMetadata videod : videos) {
                                System.out.println("[ID: " + videod.getId() + "] " + videod.getTitle() + " – " + videod.getUrl());
                            }
                            System.out.print("Enter ID of video to delete: ");
                            int idToDelete = Integer.parseInt(scanner.nextLine());

                            VideoMetadata videoToDelete = videos.stream()
                                    .filter(v -> Integer.parseInt(v.getId()) == idToDelete)
                                    .findFirst()
                                    .orElse(null);

                            if (videoToDelete == null) {
                                System.out.println("No video found with that ID.");
                                return;
                            }

                            System.out.println("You selected:");
                            System.out.println("Title: " + videoToDelete.getTitle());
                            System.out.println("URL: " + videoToDelete.getUrl());
                            System.out.print("Are you sure you want to delete this video? (yes/no): ");
                            String confirmation = scanner.nextLine().trim().toLowerCase();

                            if (confirmation.equals("yes") || confirmation.equals("y")) {
                                if (VideoDAO.deleteVideoById(idToDelete)) {
                                    System.out.println("Video deleted successfully.");
                                } else {
                                    System.out.println("Failed to delete video.");
                                }
                            } else {
                                System.out.println("Deletion cancelled.");
                            }
                        }
                        break;
                    }
                    //list tags
                    case "3": {
                        List<String> tagl = VideoDAO.getAllTags();
                        System.out.println("\nExisting Tags:");
                        if (tagl.isEmpty()) {
                            System.out.println("No tags found.");
                        } else {
                            for (int i = 0; i < tagl.size(); i++) {
                                System.out.println((i + 1) + ". " + tagl.get(i));
                            }
                        }
                        break;
                    }
                    //list vid by tag
                    case "4": {
                        System.out.print("Enter tag to search: ");
                        String searchTag = scanner.nextLine();

                        List<VideoMetadata> results = VideoDAO.getVideosByTag(searchTag);
                        if (results.isEmpty()) {
                            System.out.println("No videos found with that tag.");
                        } else {
                            System.out.println("=== Videos for tag: " + searchTag + " ===");
                            for (VideoMetadata v : results) {
                                System.out.printf("[%s] %s\n    %s\n", v.getId(), v.getTitle(), v.getUrl());
                            }
                        }
                        break;
                    }
                    //exit prog
                    case "5": {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
