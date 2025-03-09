package data.coredata.com.clip.user.entity.repository;

import com.clip.ApiApplication;
import com.clip.user.entity.User;
import com.clip.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ContextConfiguration(classes = ApiApplication.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @DisplayName("userId로 phoneNumber를 업데이트 한다.")
    @Test
    void updatePhoneNumber() {
        //given
        String phoneNumber = "010-1234-5678";
        User user = userRepository.save(User.builder().build());

        //when
        userRepository.updatePhoneNumber(user.getId(), phoneNumber);
        entityManager.clear();

        //then
        user = userRepository.findById(user.getId()).get();
        assertThat(user.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @DisplayName("다른 유저가 사용중인 번호로는 업데이트 할 수 없다.")
    @Test
    void updatePhoneNumberFail() {
        //given
        String phoneNumber = "010-1234-5678";
        User oldUser = userRepository.save(User.builder().phoneNumber(phoneNumber).build());
        User newUser = userRepository.save(User.builder().build());

        //when & then
        assertThatThrownBy(() -> userRepository.updatePhoneNumber(newUser.getId(), phoneNumber))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @DisplayName("userId로 userName을 업데이트 한다.")
    @Test
    void updateUserName() {
        //given
        String userName = "홍길동";
        User user = userRepository.save(User.builder().build());

        //when
        userRepository.updateUserName(user.getId(), userName);
        entityManager.clear();
        user = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getUsername()).isEqualTo(userName);
    }

    @DisplayName("userId로 nickname을 업데이트 한다.")
    @Test
    void updateNickname() {
        //given
        String nickname = "닉네임";
        User user = userRepository.save(User.builder().build());

        //when
        userRepository.updateNickname(user.getId(), nickname);
        entityManager.clear();
        user = userRepository.findById(user.getId()).get();

        //then
        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @DisplayName("다른 유저가 사용중인 닉네임으로 업데이트 할 수 없다.")
    @Test
    void updateNicknameFail() {
        //given
        String nickname = "닉네임";
        User oldUser = userRepository.save(User.builder().nickname(nickname).build());
        User newUser = userRepository.save(User.builder().build());

        //when & then
        assertThatThrownBy(()->userRepository.updateNickname(newUser.getId(), nickname)).isInstanceOf(DataIntegrityViolationException.class);
    }
}
