package com.example.movielist.data

import com.example.movielist.R
import com.example.movielist.model.MovieItem


class DataSetOfMovies {

    fun createList(): ArrayList<MovieItem>{
        val list = ArrayList<MovieItem>()
        list.add(
            MovieItem(
                R.drawable.blackwidow,
                "Black Widow",
                "This movie about Black Widow",
                "This is a super full description of the movie Black Widow, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.bladerunner,
                "Blade Runner",
                "This movie about Blade Runner",
                "This is a super full description of the movie Blade Runner, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.irongiant,
                "Iron Giant",
                "This movie about Iron Giant",
                "This is a super full description of the movie Iron Giant, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.it,
                "It",
                "This movie about It",
                "This is a super full description of the movie It, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.johnwick,
                "John Wick",
                "This movie about John Wick",
                "This is a super full description of the movie John Wick, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.kong,
                "Kong",
                "This movie about Kong",
                "This is a super full description of the movie Kong, and there is nothing else to say about it dude."
            )
        )

        list.add(
            MovieItem(
                R.drawable.irobots,
                "I Robots",
                "This movie about I Robots",
                "This is a super full description of the movie I Robots, and there is nothing else to say about it dude."
            )
        )

        return list
    }
}