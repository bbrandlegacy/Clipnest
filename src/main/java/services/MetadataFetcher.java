package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import models.VideoMetadata;

public class MetadataFetcher {

    public static VideoMetadata fetch(String videoUrl, String customTag) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("yt-dlp", "--skip-download", "--print-json", videoUrl);
        pb.redirectErrorStream(true);
        Process process = pb.start();


        BufferedReader reader = null;
        StringBuilder jsonOutput = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonOutput.append(line).append("\n");
            }
        } catch (IOException ioException) {
            System.out.println("Reading file is not found");
        }

            Gson gson = new Gson();
            JsonObject json;
            try {
                json = gson.fromJson(jsonOutput.toString(), JsonObject.class);

                String title = json.get("title").getAsString();
                String duration = json.get("duration_string") != null
                        ? json.get("duration_string").getAsString()
                        : json.get("duration").getAsString();
                String uploader = json.get("uploader").getAsString();
                String webpageUrl = json.get("webpage_url").getAsString();

                return new VideoMetadata(null, title, webpageUrl, duration, uploader, customTag);
            }catch (JsonSyntaxException jse) {
                System.out.println("JSON syntax error");
            }

            return null;
    }
}
