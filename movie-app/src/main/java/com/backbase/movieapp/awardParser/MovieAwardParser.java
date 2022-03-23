package com.backbase.movieapp.awardParser;

import com.backbase.movieapp.movies.model.MovieAwardInformationEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class MovieAwardParser {

  private final static String FILE_LOCATION = "src/main/resources/academy_awards.csv";
  private final static String[] HEADERS = {"year", "category", "nominee", "additional_info", "won"};

  public List<MovieAwardInformationEntity> parseCsv() {
    File file = new File(FILE_LOCATION);
    try (
        InputStream is = new FileInputStream(file);
        BufferedReader fileReader = new BufferedReader(
            new InputStreamReader(is, StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
    ) {
      List<MovieAwardInformationEntity> awardsList = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        MovieAwardInformationEntity award = MovieAwardInformationEntity.builder()
            .year(csvRecord.get(HEADERS[0]))
            .category(csvRecord.get(HEADERS[1]))
            .nominee(csvRecord.get(HEADERS[2]))
            .additionalInfo(csvRecord.get(HEADERS[3]))
            .won(csvRecord.get(HEADERS[4]))
            .build();
        awardsList.add(award);
      }
      return awardsList;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}
