package dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.Hotel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")

public class JdbcHotelDAOTest {

	@Autowired
	private HotelDAO hotelDAO;

	@Test
	public void testGetHotelById() {
		Hotel hotel = hotelDAO.getHotelById(1);

		assertEquals("1", hotel.getHotelId());
	}

	@Test
	public void testGetAllHotels() {
		List<Hotel> hotels = hotelDAO.getAllHotels();

		assertEquals(51, hotels.size());
	}

	@Test
	public void testFindHotelsByCriteria() {

		List<Hotel> hotels = hotelDAO.findHotelsByCriteria("", "0", "350", "5");
		assertEquals(15, hotels.size());
	}

}
