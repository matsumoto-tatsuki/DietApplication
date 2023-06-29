drop table users, users_favorite_diet, memo, diet_info, diet_detail, diet_select, diet_result, category, weight;
DROP TYPE difficulty_enum;
CREATE TYPE difficulty_enum AS ENUM('easy', 'normal', 'hard');

CREATE TABLE users (
  id serial PRIMARY KEY,
  user_id varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  weight integer,
  user_name varchar(50),
  user_symbol varchar(255),
  permission int,
  insert_date date
);

CREATE TABLE users_favorite_diet (
  id serial PRIMARY KEY,
  user_id varchar(50),
  diet_id int
);

CREATE TABLE diet_select (
  id serial PRIMARY KEY,
  user_id varchar(50),
  diet_id int,
  action varchar(255),
  start_date date,
  end_date date
);

CREATE TABLE diet_info (
  id serial PRIMARY KEY,
  diet_name varchar(50),
  category_id int,
  difficulty difficulty_enum
);

CREATE TABLE diet_detail (
  id serial PRIMARY KEY,
  diet_id int,
  title varchar(255),
  detail varchar(1000),
	img_path varchar(255)
);

CREATE TABLE diet_result (
  id serial PRIMARY KEY,
  user_select_id int,
  result boolean,
  date date
);

CREATE TABLE category (
  id serial PRIMARY KEY,
  type varchar(50)
);

CREATE TABLE memo (
  id serial PRIMARY KEY,
  user_id varchar(50),
  memo varchar(2000),
  date date
);

CREATE TABLE weight (
  id serial PRIMARY KEY,
  user_id varchar(50),
  weight int,
  date date
);


INSERT INTO users (user_id, password, user_name, permission, insert_date)
VALUES ('admin', 'admin', 'admin', 1, current_date);

INSERT INTO users (user_id, password, user_name, permission, insert_date)
VALUES ('user', 'user', 'user', 2, current_date-1);

-- user_selectテーブルにレコードを挿入
INSERT INTO diet_select (user_id, diet_id, action, start_date, end_date)
VALUES
  ('user', 1, '30分歩く', '2023-06-19', '2023-06-30'),
  ('user', 2, '25m泳ぐ', '2023-06-19', '2023-06-30');

-- 運動カテゴリのデータ
INSERT INTO diet_info (diet_name, category_id, difficulty)
VALUES
('ウォーキングダイエット', 1, 'easy'),
('水泳ダイエット', 1, 'hard'),
('ストレッチダイエット', 1, 'normal'),
('エアロビクスダイエット', 1, 'normal'),
('ヨガダイエット', 1, 'normal'),
('ピラティスダイエット', 1, 'normal'),
('筋トレダイエット', 1, 'hard'),
('糖質制限ダイエット', 2, 'hard'),
('脂質制限ダイエット', 2, 'hard'),
('タンパク質ダイエット', 2, 'easy'),
('野菜ダイエット', 2, 'easy'),
('果物ダイエット', 2, 'easy'),
('ヨーグルトダイエット', 2, 'easy'),
('豆腐ダイエット', 2, 'easy'),
('こんにゃくダイエット', 2, 'easy'),
('納豆ダイエット', 2, 'easy'),
('玄米ダイエット', 2, 'easy');

INSERT INTO diet_detail (diet_id, title, detail, img_path)
VALUES
    (1, 'ウォーキングダイエット', '一日30分程度、ゆっくりと歩く。階段を上り下りしたり、坂道を歩いたりすると、より効果的。', '/images/diet_detail/walking/walking.gif'),
    (2, '水泳ダイエット', '水の抵抗を利用することで、脂肪燃焼効果が高くなる。水泳は、関節への負担が少ないため、運動が苦手な人にもおすすめ。', '/images/diet_detail/swimming/swimming.png'),
    (3, 'ストレッチダイエット', '筋肉をほぐし、柔軟性を高める。運動前後に行うことで、怪我の予防にもなる。', '/images/diet_detail/stretching/stretching.gif'),
    (4, 'エアロビクスダイエット', '有酸素運動の一種で、脂肪燃焼効果が高い。自宅でエアロビクスマシンを使用するのも良い。', '/images/diet_detail/aerobics/aerobics.png'),
    (5, 'ヨガダイエット', '筋肉を鍛え、柔軟性を高める。ストレス解消にも効果的。', '/images/diet_detail/yoga/yoga.png'),
    (6, 'ピラティスダイエット', '体幹を鍛える。姿勢を良くする効果もある。', '/images/diet_detail/pilates/pilates.png'),
    (7, 'プランク', 'プランクは、腹筋や背筋などのコア部分を鍛える効果があります。うつ伏せの状態で手を肩幅に広げ、つま先と肘を支点に体をまっすぐ伸ばします。この姿勢を保ちながら30秒から1分間キープすると効果的です。', '/images/diet_detail/dead_weight/plank.gif'),
	  (7, '腕立て伏せ', '腕立て伏せは、胸や上腕三頭筋を鍛える効果があります。うつ伏せの状態から手を肩幅に広げ、肘を曲げながら上体を下げます。胸が床に触れる直前まで下げたら、力を入れて元の位置まで押し上げます。初心者は膝をついた状態から行うと良いでしょう。1セットにつき10回から15回程度行います。', '/images/diet_detail/dead_weight/pushup.gif'),
	  (7, 'スクワット', 'スクワットは、大腿四頭筋やお尻の筋肉を鍛える効果があります。立ち姿勢から、腰を落としながら膝を曲げ、太ももが平行になるようにします。その後、力を入れて元の立ち姿勢に戻ります。1セットにつき10回から15回程度行います。', '/images/diet_detail/dead_weight/squat.gif'),
		(8, '糖質制限ダイエット', '糖質を制限することで、脂肪燃焼効果が高くなる。ご飯やパンなどの主食を減らす。', '/images/diet_detail/low_carb/low_carb.png'),
    (9, '脂質制限ダイエット', '脂質を制限することで、カロリーを抑えることができる。揚げ物や脂っこい肉類を控える。', '/images/diet_detail/low_fat/low_fat.png'),
    (10, 'タンパク質ダイエット', 'タンパク質を摂取することで、筋肉量を増やすことができる。肉、魚、卵、大豆製品などを積極的に食べる。', '/images/diet_detail/protein/protein.png'),
    (11, '野菜ダイエット', '野菜をたくさん食べることで、カロリーを抑え、食物繊維を摂取することができる。毎日の食事に野菜をたっぷり取り入れる。', '/images/diet_detail/vegetable/vegetable.png'),
    (12, '果物ダイエット', '果物は糖質が多く含まれているので、食べ過ぎに注意する。食事の前に果物を食べると、満腹感を得ることができる。', '/images/diet_detail/fruit/fruit.png'),
    (13, 'ヨーグルトダイエット', 'ヨーグルトはタンパク質やカルシウムが多く含まれているので、ダイエットにおすすめ。朝食にヨーグルトを食べると、一日のエネルギーを補うことができる。', '/images/diet_detail/yogurt/Yogurt.png'),
    (14, '豆腐ダイエット', '豆腐はタンパク質やカルシウムが多く含まれているので、ダイエットにおすすめ。肉の代わりに豆腐を料理に使う。', '/images/diet_detail/tofu/Tofu.png'),
    (15, 'こんにゃくダイエット', 'こんにゃくはカロリーが低く、食物繊維が豊富に含まれているので、ダイエットにおすすめ。肉の代わりにこんにゃくを料理に使う。', '/images/diet_detail/konjac/Konjac.png'),
    (16, '納豆ダイエット', '納豆はタンパク質やカルシウムが多く含まれているので、ダイエットにおすすめ。朝食に納豆を食べると、一日のエネルギーを補うことができる。', '/images/diet_detail/natto/Natto.png'),
    (17, '玄米ダイエット', '玄米は白米に比べて食物繊維が多く含まれているので、ダイエットにおすすめ。白米の代わりに玄米を食べる。', '/images/diet_detail/brown_rice/BrownRice.png');

INSERT INTO diet_result (user_select_id, result, date) VALUES
(1,true,'2023-06-19'),
(2,false,'2023-06-19'),
(1,true,'2023-06-20'),
(2,true,'2023-06-20'),
(1,true,'2023-06-21'),
(2,false,'2023-06-21'),
(1,true,'2023-06-22'),
(2,true,'2023-06-22'),
(1,false,'2023-06-23'),
(2,false,'2023-06-23');

INSERT INTO category(type)
VALUES('運動'),('食事');

INSERT INTO memo (user_id, memo, date) VALUES
('user','ウォーキング40分歩いた～。','2023-06-19'),
('user','30分泳いだー。','2023-06-20'),
('user','散歩気持ちよかった。','2023-06-21'),
('user','風呂最高!!','2023-06-22'),
('user','ちょっと瘦せてきたかも。','2023-06-23');

INSERT INTO weight (user_id, weight, date) VALUES
('user',0,'2023-06-19'),
('user',0,'2023-06-20'),
('user',0,'2023-06-21'),
('user',0,'2023-06-22'),
('user',0,'2023-06-23');