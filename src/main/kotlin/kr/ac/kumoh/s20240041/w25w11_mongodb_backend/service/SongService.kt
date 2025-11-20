package kr.ac.kumoh.s20240041.w25w11_mongodb_backend.service

import kr.ac.kumoh.s20240041.w25w11_mongodb_backend.model.Song
import kr.ac.kumoh.s20240041.w25w11_mongodb_backend.repository.SongRepository
import org.springframework.stereotype.Service

@Service
class SongService(
    private val repository: SongRepository,
) {
    fun getAllSongs() : List<Song> = repository.findAll()
    fun getSongById(id: String) : Song? = repository.findById(id).orElse(null)
    fun getRandomSong() : Song? {
        val songs = repository.findAll()
        if (songs.isEmpty()) return null
        return songs.random()
    }
}