package repository;

import models.VideoMetadata;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    public static void save(VideoMetadata video) throws Exception {
        Connection conn = DatabaseManager.getConnection();

        String sql = "INSERT INTO videos (title, url, duration, uploader, custom_tag) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, video.getTitle());
        stmt.setString(2, video.getUrl());
        stmt.setString(3, video.getDuration());
        stmt.setString(4, video.getUploader());
        stmt.setString(5, video.getCustomTag());

        stmt.executeUpdate();
        stmt.close();
    }

    public static boolean deleteVideoById(int id) {
        String query = "DELETE FROM videos WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            System.err.println("Error deleting video: " + e.getMessage());
            return false;
        }
    }

    public static List<VideoMetadata> getVideosByTag(String tag) throws Exception {
        Connection conn = DatabaseManager.getConnection();

        String sql = "SELECT id, title, url, duration, uploader, custom_tag FROM videos WHERE custom_tag = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tag);

        ResultSet rs = stmt.executeQuery();
        List<VideoMetadata> results = new ArrayList<>();

        while (rs.next()) {
            results.add(new VideoMetadata(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("url"),
                    rs.getString("duration"),
                    rs.getString("uploader"),
                    rs.getString("custom_tag")
            ));
        }

        rs.close();
        stmt.close();
        return results;
    }

    public static List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        String query = "SELECT DISTINCT custom_tag FROM videos ORDER BY custom_tag";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tags.add(rs.getString("custom_tag"));
            }

        } catch (SQLException | IOException e) {
            System.err.println("Error fetching tags: " + e.getMessage());
        }

        return tags;
    }



}
