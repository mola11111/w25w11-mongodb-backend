package kr.ac.kumoh.s20240041.w25w11_mongodb_backend.repository

import kr.ac.kumoh.s20240041.w25w11_mongodb_backend.model.Song
import org.springframework.data.mongodb.repository.MongoRepository

interface SongRepository : MongoRepository<Song, String> {
    fun findBySinger(singer : String): List<Song>
}