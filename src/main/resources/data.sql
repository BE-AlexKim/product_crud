INSERT tb_user_info(user_id,uuid,login_id, login_pw, phone_number, user_name, user_role, create_at)
VALUES
       (1,'test-user-001','rpp0321','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01012345678','홍길동','ROLE_ADMIN',now()),
       (2,'test-user-002','joy585','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765432','홍석천','ROLE_PARTNER',now()),
       (3,'test-user-003','joy001','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765431','강호동','ROLE_PARTNER',now()),
       (4,'test-user-004','joy002','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765466','이수근','ROLE_PARTNER',now()),
       (5,'test-user-005','joy003','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765477','리사','ROLE_PARTNER',now()),
       (6,'test-user-006','joy004','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765445','잔나','ROLE_PARTNER',now()),
       (7,'test-user-007','joy005','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765234','케이틀린','ROLE_PARTNER',now()),
       (8,'test-user-008','joy006','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098762312','베인','ROLE_PARTNER',now()),
       (9,'test-user-009','joy007','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01098765434','라미안','ROLE_PARTNER',now()),
       (10,'test-user-010','showx66','$2a$10$HeaZl05.dfBfQag65Cf5wOiwaJubMjlr/Ld5JjFbi1qc1.PSdpD5e','01021323425','성춘향','ROLE_USER',now());

# INSERT tb_product_info(product_id, user_id, product_title, product_content, product_posting_status, product_pridce, create_at)
# VALUES
#        (1,2,'낭만과 사랑이 가득한 거리_실내 1탄','콘텐츠 내용','TEMPORARY',99000,now()),
#        (2,2,'translate_code_01','translate_code_02','UNDER_REVIEW',59000,now()),
#        (3,2,'낭만과 사랑이 가득한 거리_실내 3탄','콘텐츠 내용','CLEAR_REVIEW',49000,now()),
#        (4,2,'낭만과 사랑이 가득한 거리_실내 4탄','콘텐츠 내용','TEMPORARY',39000,now()),
#        (5,2,'낭만과 사랑이 가득한 거리_실내 5탄','콘텐츠 내용','REJECT_REVIEW',29000,now()),
#        (6,2,'낭만과 사랑이 가득한 거리_실내 6탄','콘텐츠 내용','CLEAR_REVIEW',19000,now()),
#        (7,2,'낭만과 사랑이 가득한 거리_실내 7탄','콘텐츠 내용','ASK_REVIEW',9000,now()),
#        (8,2,'낭만과 사랑이 가득한 거리_실내 8탄','콘텐츠 내용','TEMPORARY',5000,now()),
#        (9,3,'사랑이 가득한 거리_실내 1탄','콘텐츠 내용','TEMPORARY',99000,now()),
#        (10,3,'사랑이 가득한 거리_실내 2탄','콘텐츠 내용','ASK_REVIEW',30200,now()),
#        (11,3,'사랑이 가득한 거리_실내 3탄','콘텐츠 내용','UNDER_REVIEW',129000,now()),
#        (12,3,'사랑이 가득한 거리_실내 4탄','콘텐츠 내용','UNDER_REVIEW',109000,now()),
#        (13,3,'사랑이 가득한 거리_실내 5탄','콘텐츠 내용','TEMPORARY',229000,now()),
#        (14,3,'사랑이 가득한 거리_실내 6탄','콘텐츠 내용','REJECT_REVIEW',13000,now()),
#        (15,3,'사랑이 가득한 거리_실내 7탄','콘텐츠 내용','CLEAR_REVIEW',41000,now()),
#        (16,3,'사랑이 가득한 거리_실내 8탄','콘텐츠 내용','CLEAR_REVIEW',32000,now()),
#        (17,4,'가득한 거리_실내 1탄','콘텐츠 내용','TEMPORARY',9900,now()),
#        (18,4,'가득한 거리_실내 2탄','콘텐츠 내용','ASK_REVIEW',54000,now()),
#        (19,4,'가득한 거리_실내 3탄','콘텐츠 내용','REJECT_REVIEW',49500,now()),
#        (20,4,'가득한 거리_실내 4탄','콘텐츠 내용','UNDER_REVIEW',33000,now()),
#        (21,4,'가득한 거리_실내 5탄','콘텐츠 내용','TEMPORARY',1000,now()),
#        (22,4,'가득한 거리_실내 6탄','콘텐츠 내용','ASK_REVIEW',12000,now()),
#        (23,4,'가득한 거리_실내 7탄','콘텐츠 내용','CLEAR_REVIEW',32000,now()),
#        (24,4,'가득한 거리_실내 8탄','콘텐츠 내용','CLEAR_REVIEW',51400,now());
