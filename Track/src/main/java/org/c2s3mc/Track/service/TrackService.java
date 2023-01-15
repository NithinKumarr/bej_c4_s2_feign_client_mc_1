package org.c2s3mc.Track.service;

import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.domain.User;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.c2s3mc.Track.proxy.UserProxy;
import org.c2s3mc.Track.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;
import java.util.List;

@Service
public class TrackService implements ITrackService{

    UserProxy userProxy;
    TrackRepository trackRepository;
    @Autowired
    public TrackService(UserProxy userProxy, TrackRepository trackRepository) {
        this.userProxy = userProxy;
        this.trackRepository = trackRepository;
    }


    @Override
    public List<User> getAllUser() {
        return trackRepository.findAll();
    }

    @Override
    public User addUser(User user) throws TrackAlreadyExistException {
        if(trackRepository.findById(user.getEmail()).isEmpty()){
            userProxy.saveData(user);
            return trackRepository.save(user);
        }
        throw new TrackAlreadyExistException();
    }

    @Override
    public String deleteUser(String id) throws TrackNotFoundException {
        if(trackRepository.findById(id).isEmpty()){
            throw new TrackNotFoundException();
        }
        trackRepository.deleteById(id);
        return"User Deleted Successfully";
    }
//    @Override
//    public List<Track> getAllTrack() {
//        return trackRepository.findAll();
//    }
//
//    @Override
//    public Track addTrack(Track track) throws TrackAlreadyExistException {
//
//    if(trackRepository.findById(track.getTrackId()).isEmpty()){
//        return trackRepository.save(track);
//    }
//       throw new TrackAlreadyExistException();
//    }
//
//    @Override
//    public String deleteTrack(int id) throws TrackNotFoundException {
//    if(trackRepository.findById(id).isEmpty()){
//        throw new TrackNotFoundException();
//    }
//     trackRepository.deleteById(id);
//        return"Track Deleted Successfully";
//
//    }
//
//    @Override
//    public List<Track> findByTrackRating(int rating) throws TrackRatingNotFoundException {
//    if(trackRepository.findByTrackRating(rating).isEmpty()){
//        throw new TrackRatingNotFoundException();
//    }
//        return trackRepository.findByTrackRating(rating) ;
//    }
//
//    @Override
//    public List<Track> findAllByArtistName(String artistName) throws ArtistNameNotFoundException {
//       if(trackRepository.findAllByArtistName(artistName).isEmpty()){
//           throw new ArtistNameNotFoundException();
//       }
//       return trackRepository.findAllByArtistName(artistName);
//    }



}
