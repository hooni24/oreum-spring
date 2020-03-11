package com.oreumdiary.service.posts;

import com.oreumdiary.domain.posts.Posts;
import com.oreumdiary.domain.posts.PostsRepository;
import com.oreumdiary.web.dto.PostsListResponseDto;
import com.oreumdiary.web.dto.PostsResponseDto;
import com.oreumdiary.web.dto.PostsSaveRequestDto;
import com.oreumdiary.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        /*
            여기서 신기한 점은.. posts 엔티티의 필드값만 바꾸었고, DB에 업데이트를 명령하는 부분이 없다는 것이다.
            그럼에도 불구하고 업데이트가 이루어지는 이유는 영속성 컨텍스트가 유지되고 있기 때문인데,
            JPA의 엔티티매니저가 활성화된 상태로 데이터를 가져오면 영속성 컨텍스트가 유지된 상태이다
            ( Spring Data Jpa를 쓰는 경우는 기본설정값임)

            따라서, 이 경우에는 findById()를 통해 가져온 엔티티의 값을 변경하는것만으로도
            트랜잭션 종료와 동시에 테이블에 변경된 값을 반영한다.

            즉, 엔티티 객체의 필드값만 변경하면 별도로 쿼리를 날릴 필요가 없는 것인데
            이는 Dirty Checking 이라고 부른다.
         */

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }

}
