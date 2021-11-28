package com.pluralsight.repository;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.pluralsight.model.Ride;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ride createRide(Ride ride) {
/*		jdbcTemplate.update("INSERT INTO ride (name, duration) VALUES (?, ?)",
													ride.getName(), ride.getDuration());*/
/*		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pS = con.prepareStatement(
						"INSERT INTO ride (name, duration) VALUES (?, ?)", new String[] {"id"});
				pS.setString(1, ride.getName());
				pS.setInt(2, ride.getDuration());
				return pS;
			}
		}, keyHolder);
		
		Number id = keyHolder.getKey();*/

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ride")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());
        insert.setTableName("ride");

        Number id = insert.executeAndReturnKey(data);

        return getRide(id.intValue());
    }

    public Ride getRide(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ride WHERE id = ?"
                , new RideRowMapper(), id);
    }

    @Override
    public Ride updateRide(Ride ride) {
        jdbcTemplate.update("UPDATE ride SET name = ?, duration = ? WHERE id = ?",
                ride.getName(), ride.getDuration(), ride.getId());
        return ride;
    }

    @Override
    public void updateRides(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("UPDATE ride SET ride_date = ? WHERE id = ?", pairs);
    }

    @Override
    public void deleteRide(Integer id) {
        jdbcTemplate.update("DELETE FROM ride WHERE id = ?", id);
    }

    @Override
    public List<Ride> getRides() {

/*		Ride ride = new Ride();
		ride.setName("Corner Canyon");
		ride.setDuration(120);
		List <Ride> rides = new ArrayList<>();
		rides.add(ride);*/
        return jdbcTemplate.query("SELECT * FROM ride", new RideRowMapper());
    }



}
