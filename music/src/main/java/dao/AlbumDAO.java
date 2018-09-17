package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import model.*;
import java.util.Collection;
import java.util.ArrayList;

public class AlbumDAO {
    private JdbcTemplate jdbcTemplate;

    public AlbumDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Album createAlbum(Album album){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("INSERT into albums(id,title) values(?,?)",album.getId(),album.getTitle());
        return album;
    }

    public Album getAlbum(int id){
        Album album = new Album(id, "");
        //TODO: Implement this CRUD function
        TrackDAO trackDAO = new TrackDAO(jdbcTemplate);
        //Get album and set tracks using getTracksByAlbumId(id) in TracksDAO
        this.jdbcTemplate.queryForObject("SELECT * FROM albums WHERE ID = ?", new Object[] {id}, (rs, rowNum) -> new Album(rs.getInt("id"), rs.getString("title")));
        album.setTracks(trackDAO.getTracksByAlbumId(id));
        return album;
    }

    public Collection<Album> getAllAlbums(){
        Collection<Album> albums = new ArrayList<Album>();
        this.jdbcTemplate.query(
                "SELECT * FROM albums", new Object[] { },
                (rs, rowNum) -> new Album(rs.getInt("id"), rs.getString("title"))
        ).forEach(album -> albums.add(album));

        return albums;
    }

    public Album updateAlbum(Album album){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("UPDATE albums set title = ? where id = ?",album.getTitle(),album.getId());
        return album;
    }

    public boolean deleteAlbum(Album album){
        boolean success = false;
        //TODO: Implement this CRUD function
        success = this.jdbcTemplate.update("DELETE from albums where id =?",album.getId()) > 0;
        return success;
    }



}
