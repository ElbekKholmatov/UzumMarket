package uz.market.uzum.services;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import uz.market.uzum.domains.Destination;
import uz.market.uzum.repositories.DestinationRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;


    public String convertToAddress(double latitude, double longitude) {

        String urlString = "https://nominatim.openstreetmap.org/reverse?lat=" + latitude + "&lon=" + longitude + "&format=jsonv2";
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(result.toString());
            JSONObject address = json.getJSONObject("address");

            String country =address.has("country")? address.getString("country"):"";
            String country_code =address.has("country_code")? address.getString("country_code"):"";
            String residential =address.has("residential")? address.getString("residential"):"";
            String road =address.has("road")? address.getString("road"):"";
            String city =address.has("city")? address.getString("city"):"";
            String amenity =address.has("amenity")? address.getString("amenity"):"";
            String county =address.has("county")? address.getString("county"):"";

            Destination destination = destinationRepository.save(Destination.builder()
                    .county(country)
                    .country_code(country_code)
                    .residential(residential)
                    .road(road)
                    .city(city)
                    .amenity(amenity)
                    .county(county)
                    .build());
            return destination.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
