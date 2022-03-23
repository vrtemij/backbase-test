package com.backbase.movieapp.user.service.mapper;

import com.backbase.movieapp.movies.domain.OpenMovie;
import com.backbase.movieapp.user.domain.UserMovie;
import com.backbase.movieapp.user.model.UserMovieEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMovieMapper {

  String NOT_APPLICABLE = "N/A";
 
  @Mapping(target = "boxOffice", source = "openMovie", qualifiedByName = "mapBoxOffice")
   UserMovie fromOpenMovieToUserMovie(OpenMovie openMovie);

   UserMovieEntity toEntity(UserMovie userMovie);

   UserMovie toDto(UserMovieEntity userMovieEntity);

   List<UserMovie> toDtos(List<UserMovieEntity> userMovieEntities);

  @Named("mapBoxOffice")
  default Long mapBoxOffice(OpenMovie openMovie) {
    if (!NOT_APPLICABLE.equals(openMovie.getBoxOffice())) {
      String boxOfficeValueString = openMovie.getBoxOffice().replaceAll("\\D+", "");
      return Long.parseLong(boxOfficeValueString);
    }
    return 0L;
  }
}
