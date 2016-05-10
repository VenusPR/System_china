-- commodity�� ������Ʒ��Ϣ --
create table commodity
(
    commodity_number varchar2(255) primary key,
    commodity_name   varchar(255) not null,
    commodity_made   varchar2(255),
    commodity_price  NUMBER(18,2) not null,
    commodity_balance NUMBER(7) not null,
    commodity_pic    varchar2(255)not null,
    commodity_id number(11) references classify(gid) not null
);
-- Ϊcommodity������gid����Ψһ ����--
create sequence commodity_seq
       start with   1
       increment by 1
       minvalue     1
       maxvalue     100000
       nocycle
       cache        10
       
--����������
create trigger commodity_tigger
       before insert on commodity
       for each row 
         begin
           select commodity_seq.nextval into :new.commodity_number from dual;
         end;
         
         
 
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('��������Ь', '��ɽ', 180, 500, '001.jpg',5);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('��̤�˶�Ь', '����', 120, 800, '002.jpg',5);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('�Ϳ��˶�Ь', '����', 500, 1000, '003.jpg',5);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('���ϴ�˹TѪ��', '�Ϻ�', 388,600,'004.jpg',6);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('�����Ļ���', '����', 180, 900, '005.jpg',6);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('С��3', '����', 1999, 3000, '006.jpg',7);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('С��2S', '����', 1299, 1000, '007.jpg',7);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('thinkpad�ʼǱ�', '����', 6999, 500, '008.jpg',8);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('dell�ʼǱ�', '����', 3900, 500, '009.jpg',8);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('ipad5', '����', 5900, 500, '010.jpg',8);

         