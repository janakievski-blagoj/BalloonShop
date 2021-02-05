package mk.finki.ukim.wp.balloonShop;

import mk.finki.ukim.wp.balloonShop.model.User;
import mk.finki.ukim.wp.balloonShop.model.exceptions.InvalidEmailException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.balloonShop.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.balloonShop.repository.JPARepository.UserRepoJPA;
import mk.finki.ukim.wp.balloonShop.service.Impl.UserServiceImpl;
import mk.finki.ukim.wp.balloonShop.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepoJPA userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "email", "password", "name", "surname");
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }


    @Test
    public void testSuccessRegister() {
        User user = this.service.register("username","email","password", "password", "name", "surname");

        Mockito.verify(this.service).register("username", "email", "password", "password", "name", "surname");

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not mach", "name", user.getName());
        Assert.assertEquals("surname do not mach", "surname", user.getSurname());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());
        Assert.assertEquals("email do not mach", "email", user.getEmail());
    }


    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(null,"email", "password", "password", "name", "surname"));
        Mockito.verify(this.service).register(null,"email", "password", "password", "name", "surname");
    }


    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username, "email","password", "password", "name", "surname"));
        Mockito.verify(this.service).register(username, "email","password", "password", "name", "surname");
    }


    @Test
    public void testNullPassword() {
        String username = "username";
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username,"email", null, "password", "name", "surname"));
        Mockito.verify(this.service).register(username,"email", null, "password", "name", "surname");
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidUsernameOrPasswordException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.service.register(username,"email", password, "password", "name", "surname"));
        Mockito.verify(this.service).register(username, "email", password, "password", "name", "surname");
    }


    @Test
    public void testNullEmail(){
        Assert.assertThrows("InvalidEmailException expected",
                InvalidEmailException.class,
                () -> this.service.register("username", null, "password", "password", "name", "surname"));
        Mockito.verify(this.service).register("username", null, "password", "password", "name", "surname");
    }


    @Test
    public void testEmptyEmail(){
        String email = "";
        Assert.assertThrows("InvalidEmailException expected",
                InvalidEmailException.class,
                () -> this.service.register("username", email, "password", "password", "name", "surname"));
        Mockito.verify(this.service).register("username", email, "password", "password", "name", "surname");
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.service.register(username,"email", password, confirmPassword, "name", "surname"));
        Mockito.verify(this.service).register(username,"email", password, confirmPassword, "name", "surname");
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("username","email", "password", "name", "surname");
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.register(username, "email","password", "password", "name", "surname"));
        Mockito.verify(this.service).register(username,"email", "password", "password", "name", "surname");
    }
}







