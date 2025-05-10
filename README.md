## 요구 사항

### 문자열 계산기

사용자는 덧셈 외에도 뺄셈(-), 곱셈(*), 나눗셈(/) 연산 기호를 지정하여 다양한 계산을 수행할 수 있어야 한다.

계산기 결과는 정수로 반환하되, 나눗셈의 경우 소수점 둘째 자리까지 반올림하여 표현해야 한다.

계산식과 결과를 로그로 남겨, 사용자가 이전 계산 내역을 조회할 수 있어야 한다.

## 규칙 

- Java 21 Version 사용하기

- Java Standard Library 외 사용 금지

## PR 마감 

2025/04/06 자정 

## 기간

2025/03/23 ~ 2025/04/05

## 변경사항

### 1. 로그 출력을 DB, 파일 2가지로 가능하게 변경.

### 2. 입력방식 콘솔, 파일, 실행 인자 3가지로 가능하도록 변경.

### 3. 로그에 시간을 남기도록 변경.

### 4. 수식 구분자 입력받을 수 있도록 변경( 기존: "\r\n" > 변경: application.properties의 formula.seperator 값 )

## Log 저장 테이블 DDL

mysql 8.x 버전 기준으로 작성

CREATE TABLE `CalcLog` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `FORMULA` varchar(100) NOT NULL,
  `BACK_FORMULA` varchar(100) NOT NULL,
  `RESULT` varchar(100) NOT NULL,
  `DATE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='계산기 로그 저장 테이블';