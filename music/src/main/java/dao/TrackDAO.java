package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import model.*;
import java.util.Collection;
import java.util.ArrayList;


public class TrackDAO {
    private JdbcTemplate jdbcTemplate;

    public TrackDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }


    public Track createTrack(Track track){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("INSERT into tracks(id,title,album) values(?,?,?)",track.getId(),track.getTitle(),track.getAlbumId());
        return track;
    }

    public Track getTrack(int id){
        Track track = new Track(id);
        //TODO: Implement this CRUD function
        this.jdbcTemplate.queryForObject("SELECT * FROM tracks WHERE ID = ?", new Object[] {id}, (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"), rs.getInt("albumId")));
        return track;
    }

    public Collection<Track> getAllTracks(){
        Collection<Track> tracks = new ArrayList<Track>();
        //TODO: Implement this CRUD function
       this.jdbcTemplate.query(
                "SELECT * FROM tracks", new Object[] { },
                (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"), rs.getInt("albumId"))
        ).forEach(track -> tracks.add(track));
        return tracks;
    }

    public Collection<Track> getTracksByAlbumId(int albumId){
        Collection<Track> tracks = new ArrayList<Track>();

        this.jdbcTemplate.query(
                "SELECT * FROM tracks WHERE album = ?", new Object[] { albumId },
                (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"),albumId)
        ).forEach(track -> tracks.add(track) );

        return tracks;
    }
    public Track updateTrack(Track track){
        //TODO: Implement this CRUD function
        this.jdbcTemplate.update("UPDATE tracks set title = ?, album= ? where id = ?",track.getTitle(),track.getAlbumId(),track.getId());
        return track;
    }

    public boolean deleteTrack(Track track){
        boolean success = false;
        //TODO: Implement this CRUD function
        success = this.jdbcTemplate.update("DELETE from tracks where id =?",track.getId()) > 0;
        return success;
    }

}
