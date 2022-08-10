package book;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookWebProjectApplicationTests {
	
	@Autowired
	BookMapper mapper;
	
	@Order(1)
	@DisplayName("추가 테스트")
	@Test
	void insertBookTest() {
		int result = mapper.insertBook(new BookDTO("8912456712", "자바 프로그래밍", "홍길동", "J테스트", "2020-02-19"));
		System.out.println(result);
		if(result == 0)
			fail("데이터 등록에 실패하였습니다, SQL문이나 입력 데이터를 확인해보세요");
	}
	
	@Order(2)
	@DisplayName("검색 테스트")
	@Test
	void selectBookTest() {
		List<BookDTO> list = mapper.selectBook("자바");
		System.out.println(list.toString());
		if(list == null)
			fail("오류, 해당 데이터는 있는데 검색결과가 없습니다. SQL문을 확인해보세요");
	}
	
	@Order(3)
	@DisplayName("삭제 테스트")
	@Test
	void deleteBookTest() {
		int result = mapper.deleteBook("8912456712");
		System.out.println(result);
		if(result == 0)
			fail("데이터 삭제에 실패하였습니다, SQL문이나 입력 데이터를 확인해보세요");
	}

}
