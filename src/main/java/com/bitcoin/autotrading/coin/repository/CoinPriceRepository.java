package com.bitcoin.autotrading.coin.repository;
// repository 파일은 mapper라고 생각하래

import com.bitcoin.autotrading.coin.domain.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository; // JPA의 대부분의 자동 쿼리 기능 수행
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPrice, String> {
    // @Query는 커스텀 쿼리를 가능하게함 select절을 쓸수 있다는거 같음????
//    @Query(value = "select market, korean_name, english_name, market_warning from test_table where 1=1", nativeQuery = true)
//    List<CoinPrice> findByMarket(@Param("market") String market);

    List<CoinPrice> findAllByMarket(String market); // findALLBy(where조건) 이렇게 해석해도 무방
}
