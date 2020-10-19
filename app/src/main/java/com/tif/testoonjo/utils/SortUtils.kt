package com.tif.testoonjo.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    const val NOTHING = "Nothing"

    fun getSortedMovie(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieTable ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
            NOTHING -> {
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
    fun getSortedTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvShowTable ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
            NOTHING -> {
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
    fun getSortedFavMovie(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieTable WHERE favorite = 1 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
            NOTHING -> {
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
    fun getSortedFavTvShow(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvShowTable WHERE favorite = 1 ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY title ASC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
            NOTHING -> {
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}