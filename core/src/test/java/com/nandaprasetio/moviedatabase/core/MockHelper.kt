package com.nandaprasetio.moviedatabase.core

import com.nandaprasetio.moviedatabase.core.domain.entity.*
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.Genre
import com.nandaprasetio.moviedatabase.core.domain.entity.genre.GenreList
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.Movie
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetail
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieDetailCredit
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.MovieUserReview
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImage
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailimage.MovieDetailImageAttribute
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideo
import com.nandaprasetio.moviedatabase.core.domain.entity.movie.moviedetailvideo.MovieDetailVideoAttribute
import com.nandaprasetio.moviedatabase.core.domain.entity.people.Cast
import com.nandaprasetio.moviedatabase.core.domain.entity.people.Crew
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.AvatarUrlString
import com.nandaprasetio.moviedatabase.core.domain.entity.urlstring.ImageUrlString

object MockHelper {
    fun getGenreList(): GenreList {
        return GenreList(
            listOf(
                Genre("1", "Action"),
                Genre("2", "Sci-Fi"),
                Genre("3", "Thrill"),
            )
        )
    }

    fun getMoviePagingResult(): PagingResult<Movie> {
        val movieList = mutableListOf<Movie>().also {
            var movieId = 1
            getGenreList().genres.forEach { genre ->
                (1..10).forEach { index ->
                    it.add(
                        Movie(
                            id = movieId,
                            title = "${genre.name} $index",
                            adult = false,
                            backdropPath = ImageUrlString("/test"),
                            genreIds = listOf(genre.id.toInt()),
                            originalLanguange = "Indonesia",
                            originalTitle = "Action $index",
                            overview = "Sample ${genre.name}.",
                            popularity = 5.0f,
                            posterPath = ImageUrlString("/test"),
                            releaseDate = "2012-01-01",
                            video = false,
                            voteAverage = 5.0f,
                            voteCount = 1234
                        )
                    )
                    movieId += 1
                }
            }
        }
        return PagingResult(
            page = 1,
            results = movieList,
            totalPages = 1,
            totalResults = movieList.size
        )
    }

    fun getMovieDetail(): MovieDetail {
        return MovieDetail(
            id = 1,
            title = "Action 1",
            adult = false,
            backdropPath = ImageUrlString("/test"),
            genreIds = listOf(1),
            originalLanguange = "Indonesia",
            originalTitle = "Action 1",
            overview = "Sample Action.",
            popularity = 5.0f,
            posterPath = ImageUrlString("/test"),
            releaseDate = "2012-01-01",
            video = false,
            voteAverage = 5.0f,
            voteCount = 1234,
            budget = 5000000,
            homePage = "https://www.test.com",
            imdbId = "1234567890",
            productionCompanies = listOf(
                ProductionCompany(
                    id = "1",
                    logoPath = ImageUrlString("/test"),
                    name = "Test",
                    originCountry = "Indonesia"
                )
            ),
            productionCountries = listOf(
                ProductionCountry(
                    iso31661 = "1234567890",
                    name = "Test"
                )
            ),
            revenue = 500000,
            runtime = 100,
            spokenLanguages = listOf(
                SpokenLanguage(
                    iso6391 = "1234567890",
                    name = "English"
                )
            ),
            status = "Test",
            tagline = "Test"
        )
    }

    fun getMovieDetailImage(): MovieDetailImage {
        return MovieDetailImage(
            id = 1,
            backdrops = listOf(
                MovieDetailImageAttribute(
                    aspectRatio = 1.0f,
                    height = 1,
                    iso6391 = "",
                    filePath = ImageUrlString("/test"),
                    voteAverage = 5.0f,
                    voteCount = 10,
                    width = 10
                )
            ),
            posters = listOf(
                MovieDetailImageAttribute(
                    aspectRatio = 1.0f,
                    height = 1,
                    iso6391 = "1234567890",
                    filePath = ImageUrlString("/test"),
                    voteAverage = 5.0f,
                    voteCount = 10,
                    width = 10
                )
            )
        )
    }

    fun getMovieDetailVideo(): MovieDetailVideo {
        return MovieDetailVideo(
            id = 1,
            results = listOf(
                MovieDetailVideoAttribute(
                    iso6391 = "1234567890",
                    id = "1",
                    iso31661 = "1234567890",
                    key = "test-key",
                    name = "Test",
                    site = "https://www.test.com",
                    size = 10,
                    type = "youtube"
                )
            )
        )
    }

    fun getMovieDetailCredit(): MovieDetailCredit {
        return MovieDetailCredit(
            id = 1,
            cast = listOf(
                Cast(
                    id = 1,
                    adult = false,
                    gender = 1,
                    knownForDepartment = "Unknown",
                    name = "Test",
                    originalName = "Test",
                    popularity = 5.0f,
                    profilePath = ImageUrlString("/test"),
                    castId = 1,
                    character = "Test",
                    creditId = "1",
                    order = 1
                )
            ),
            crew = listOf(
                Crew(
                    id = 1,
                    adult = false,
                    gender = 1,
                    knownForDepartment = "Unknown",
                    name = "Test",
                    originalName = "Test",
                    popularity = 5.0f,
                    profilePath = ImageUrlString("/test"),
                    creditId = "1",
                    department = "Test",
                    job = "Test"
                )
            )
        )
    }

    fun getMovieUserReview(): PagingResult<MovieUserReview> {
        val movieUserReview = mutableListOf<MovieUserReview>().also {
            it.add(
                MovieUserReview(
                    id = "1",
                    author = "Test",
                    authorDetail = AuthorDetail("Test", "test", AvatarUrlString("/test"), 5.0f),
                    content = "Test",
                    createdAt = "2012-01-12",
                    url = "https://www.test.com"
                )
            )
        }
        return PagingResult(
            page = 1,
            results = movieUserReview,
            totalPages = 2,
            totalResults = 12
        )
    }
}