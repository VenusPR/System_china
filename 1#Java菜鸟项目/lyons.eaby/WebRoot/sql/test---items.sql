CREATE TABLE items 
(
  id number(11) PRIMARY KEY,
  name varchar(50) default NULL,
  city varchar(50) default NULL,
  price number(11) default NULL,
  num number(11) default NULL,
  picture varchar(500) default NULL
)
------------

CREATE SEQUENCE items_seq
       START WITH      1
       INCREMENT BY    1
       MINVALUE        1
       MAXVALUE     100000
       NOCYCLE
       CACHE           10
       
--������

CREATE TRIGGER  items_trigger
       BEFORE INSERT ON items 
       FOR EACH ROW 
       BEGIN
           SELECT items_seq.nextval into :new.id FROM dual;
       END
commit;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO items VALUES ('1', '��������Ь', '��ɽ', '180', '500', '001.jpg');
INSERT INTO items VALUES ('2', '��̤�˶�Ь', '����', '120', '800', '002.jpg');
INSERT INTO items VALUES ('3', '�Ϳ��˶�Ь', '����', '500', '1000', '003.jpg');
INSERT INTO items VALUES ('4', '���ϴ�˹TѪ��', '�Ϻ�', '388', '600', '004.jpg');
INSERT INTO items VALUES ('5', '�����Ļ���', '����', '180', '900', '005.jpg');
INSERT INTO items VALUES ('6', 'С��3', '����', '1999', '3000', '006.jpg');
INSERT INTO items VALUES ('7', 'С��2S', '����', '1299', '1000', '007.jpg');
INSERT INTO items VALUES ('8', 'thinkpad�ʼǱ�', '����', '6999', '500', '008.jpg');
INSERT INTO items VALUES ('9', 'dell�ʼǱ�', '����', '3999', '500', '009.jpg');
INSERT INTO items VALUES ('10', 'ipad5', '����', '5999', '500', '010.jpg');

commit;
