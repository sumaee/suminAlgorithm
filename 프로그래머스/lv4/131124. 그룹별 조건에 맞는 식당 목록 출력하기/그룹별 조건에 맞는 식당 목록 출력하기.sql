-- 코드를 입력하세요
SELECT m.member_name AS MEMBER_NAME, r.review_text AS REVIEW_TEXT, DATE_FORMAT(r.review_date, "%Y-%m-%d") AS REVIEW_DATE
FROM MEMBER_PROFILE AS m JOIN REST_REVIEW AS r 
ON r.member_id = m.member_id
WHERE m.member_id = (SELECT member_id FROM rest_review
                    GROUP BY member_id
                    ORDER BY COUNT(*) DESC LIMIT 1)
                    ORDER BY REVIEW_DATE asc, REVIEW_TEXT;