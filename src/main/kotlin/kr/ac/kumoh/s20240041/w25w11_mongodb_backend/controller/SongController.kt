package kr.ac.kumoh.s20240041.w25w11_mongodb_backend.controller

import kr.ac.kumoh.s20240041.w25w11_mongodb_backend.model.Song
import kr.ac.kumoh.s20240041.w25w11_mongodb_backend.service.SongService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/songs")
class SongController(private val service: SongService, ) {
    @GetMapping
    fun getAllSongs() : List<Song> = service.getAllSongs()

    @GetMapping("/{id}")
    fun getSongById(@PathVariable id : String) : Song? = service.getSongById(id)

    @GetMapping("/random")
    fun getRandomSong() : Song? = service.getRandomSong()

}