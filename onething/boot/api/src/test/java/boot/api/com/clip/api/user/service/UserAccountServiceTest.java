package boot.api.com.clip.api.user.service;

import com.clip.ApiApplication;
import com.clip.api.user.controller.dto.LoginDto;
import com.clip.api.user.controller.dto.SignupDto;
import com.clip.api.user.exception.NotFoundUserException;
import com.clip.api.user.service.UserAccountService;
import com.clip.global.config.jwt.JWTProperties;
import com.clip.global.config.jwt.TokenProvider;
import com.clip.user.entity.Platform;
import com.clip.user.entity.User;
import com.clip.user.repository.UserRepository;
import com.clip.user.service.UserService;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = ApiApplication.class)
@SpringBootTest
public class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenProvider tokenProvider;
    @MockitoBean
    JWTProperties jwtProperties;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("SocialId와 Platform으로 User를 생성 후 UserId가 담긴 JWT를 반환한다.")
    @Test
    void signup() {
        //given
        String socialId = "socialId";
        Platform platform = Platform.APPLE;
        String secretKey = Base64.getEncoder().encodeToString(Jwts.SIG.HS256.key().build().getEncoded());
        SignupDto signupDto = SignupDto.builder()
                .marketingPermission(true)
                .servicePermission(true)
                .privatePermission(true)
                .socialId(socialId)
                .platform(platform)
                .build();
        given(jwtProperties.getSecretKey()).willReturn(secretKey);
        given(jwtProperties.getAccessTokenExpirationPeriodDay()).willReturn(1);

        //when
        TokenProvider.Token token = userAccountService.signup(signupDto);
        String userId = tokenProvider.extractUserId(token.accessToken());
        User user = userService.findOptUser(socialId, platform).get();

        //then
        assertThat(Long.parseLong(userId)).isEqualTo(user.getId());
    }

    @DisplayName("SocialId와 Platform으로 User를 찾아 JWT를 반환한다.")
    @Test
    void login() {
        //given
        String socialId = "socialId";
        Platform platform = Platform.APPLE;
        String secretKey = Base64.getEncoder().encodeToString(Jwts.SIG.HS256.key().build().getEncoded());
        userService.save(User.builder()
                .socialId(socialId)
                .platform(platform)
                .build());
        given(jwtProperties.getSecretKey()).willReturn(secretKey);
        given(jwtProperties.getAccessTokenExpirationPeriodDay()).willReturn(1);

        //when
        TokenProvider.Token token = userAccountService.login(LoginDto.builder().platform(platform).socialId(socialId).build());
        String userId = tokenProvider.extractUserId(token.accessToken());
        User user = userService.findOptUser(socialId, platform).get();

        //then
        assertThat(Long.parseLong(userId)).isEqualTo(user.getId());
    }

    @DisplayName("SocialId와 Platform으로 User를 검색 후 기가입 유저가 아니면 NotFoundUserException이 발생한다.")
    @Test
    void loginFail() {
        //given
        String socialId = "socialId";
        Platform platform = Platform.APPLE;
        String secretKey = Base64.getEncoder().encodeToString(Jwts.SIG.HS256.key().build().getEncoded());

        given(jwtProperties.getSecretKey()).willReturn(secretKey);
        given(jwtProperties.getAccessTokenExpirationPeriodDay()).willReturn(1);

        //when & then
        assertThatThrownBy(() -> userAccountService.login(LoginDto.builder().platform(platform).socialId(socialId).build()))
                .isInstanceOf(NotFoundUserException.class);
    }
}
