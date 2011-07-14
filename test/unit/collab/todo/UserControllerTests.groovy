package collab.todo



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerTests {

	User user
	UserController uc

	void setUp() {
		user = new User(userName:"User1", firstName:"User1FN", lastName:"User1LN")
		user.save()
		uc = new UserController()
	}

	void tearDown() {
		user.delete()
	}

	void testHandleLogin() {
		uc.params.userName = user.userName
		uc.handleLogin()
		def sessUser = uc.session.user
		assert sessUser
		assertEquals("Ids ke deben coincidir", user.id, sessUser.id)
		assertEquals "/todo", uc.response.redirectedUrl
	}

	void testHandleLoginInvalidUser() {
		uc.params.userName = "INVALID_USER_NAME"
		uc.handleLogin()
		assertEquals "/user/login", uc.response.redirectedUrl
		def message = uc.flash.message
		assert message
		assert message.startsWith("User not found")
	}

	void testLogout() {
		uc.session.user = user
		uc.logout()
		def sessUser = uc.session.user
		assertNull("Se espera ke la sesión de usuario sea null", sessUser)
		assertEquals "/user/login", uc.response.redirectedUrl
	}
}
