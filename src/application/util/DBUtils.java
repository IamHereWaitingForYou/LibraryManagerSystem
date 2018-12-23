package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	//jdbc驱动
	static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	//路径
	static final String url = "jdbc:derby:";
	//数据库名称
	static final String dbName = "Derby_LibraryManageSystem";
	
	
	/**
	 * 创建数据库并插入测试数据。
	 * 
	 * @return 返回0代表数据库已经存在，取消创建。返回1代表创建成功，返回-1代表创建失败。
	 */
	public static int createDB() {
		Connection con = null;
		if (!dbExists()) {// 如果数据库不存在，则创建数据库并插入测试数据。
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url + dbName + ";create=true");

				String createUserTableSqlStr = "CREATE TABLE users(" + "account VARCHAR(20)  NOT NULL,"
						+ "password VARCHAR(33)," + "userName VARCHAR(20)," + "status VARCHAR(20)," + "borrowNumber int," 
						+ "borrowNumberSum int default 0," + "forfeit int default 0," + "grade int," + "academy VARCHAR(20)," + "clbum VARCHAR(20)," 
						+ "sex VARCHAR(20)," + "phoneNumber VARCHAR(20)," + "IcNumber VARCHAR(20)," + "initialpassword VARCHAR(20))";

				String createBookTableSqlStr = "CREATE TABLE books(" + "bookId VARCHAR(20)  NOT NULL," + "name VARCHAR(50)," + "type VARCHAR(20),"
						+ "author VARCHAR(50)," + "translator VARCHAR(50)," + "publishHouse VARCHAR(50),"  + "publishTime VARCHAR(20),"
						+ "reserve int," + "margin int," + "price DOUBLE," + "introduction VARCHAR(500)," + "shopin VARCHAR(20))";

				String createBorrowTableSqlStr = "CREATE TABLE borrows("  + "borrowId VARCHAR(20) NOT NULL," + "userName VARCHAR(20),"
						 + "bookName VARCHAR(20),"  + "borrowDate VARCHAR(20)," + "dueDate VARCHAR(20)," + "renew int default 0,"  + "giveBack Boolean default false,"
						 + "giveBackDate VARCHAR(20) default '无') ";
				
				String createCookieSqlStr = "CREATE TABLE cookies("  + "account VARCHAR(20) NOT NULL," + "password VARCHAR(33),"
						 + "status VARCHAR(20))";
				
				String insertUserSqlStr = "INSERT INTO users(account, password, userName, status, borrowNumber,borrowNumberSum, sex, phoneNumber, IcNumber,initialpassword) VALUES "
						+ "('2017250401180','000000','林子珉','系统管理员',10,0,'男','15521468292','2017250401180','046615'),"
						+ "('2017250401130','000000','李桂鸿','系统管理员',10,0,'男','15622355594','2017250401130','046615'),"
						+ "('2017250401230','000000','潘永佳','系统管理员',10,0,'男','13007862257','2017250401230','046615'),"
						+ "('2017250401240','000000','王明岱','系统管理员',10,0,'男','18955695479','2017250401240','046615'),"
						+ "('2017250401181','111111','林子珉','图书管理员',10,0,'男','15521468292','2017250401181','046615'),"
						+ "('2017250401131','111111','李桂鸿','图书管理员',10,0,'男','15622355594','2017250401131','046615'),"
						+ "('2017250401231','111111','潘永佳','图书管理员',10,0,'男','13007862257','2017250401231','046615'),"
						+ "('2017250401241','111111','王明岱','图书管理员',10,0,'男','18955695479','2017250401241','046615'),"
						+ "('201725040118','123456','林子珉','读者',10,1,'男','15521468292','440582199905046615','046615'),"
						+ "('201725040113','123456','李桂鸿','读者',10,1,'男','15622355594','440582199205046615','046615'),"
						+ "('201725040123','123456','潘永佳','读者',10,1,'男','13007862257','440882199804151818','151818'),"
						+ "('201725040124','123456','王明岱','读者',10,1,'男','18955695479','340803199908032312','032312'),"
						+ "('201725040126','123456','徐斌','读者',10,1,'男','18955695479','440582199205046615','046615')";
				
				String insertBookSqlStr = "INSERT INTO books(bookId, name, type, author, translator, publishHouse, publishTime, reserve, margin, price, introduction,shopin) VALUES"
						//6本计算机、网络
						+"('1','JAVA语言程序设计','计算机','梁勇','戴开宇','机械工业出版社','2017-12-11',5,3,85,'机械工业出版社出版的JAVA入门书籍，热门畅销书','2018-05-01'),"
							+"('2','数据库基础及应用','计算机','魏善沛、何海江','魏善沛、何海江','机械工业出版社','2017-09-01',5,5,85,'机械工业出版社出版的数据库入门书籍，热门畅销书','2018-05-01'),"
							+"('3','Python编程 从入门到实践','程序设计','【美】埃里克·马瑟斯(Eric Matthes)','袁国忠','人民邮电出版社','2016-07-01',10,8,84,'针对不同层次Python读者的入门书，用Python编程的基本概念，把理论付诸实践来开发项目','2018-05-01'),"
							+"('4','C Primer Plus','程序设计','【美】史蒂芬·普拉达(Stephen Prata)','姜佑','人民邮电出版社','2016-04-01',7,5,84.5,'详细讲解C语言基本概念与编程技巧，适用于C语言初学者以及进一步提升编程技巧的程序员','2018-05-01'),"
							+"('5','算法(第四版)','程序设计','塞奇威客(Robert Sedgewick)/韦恩(Kewin Wayne)','谢路云','人民邮电出版社','2012-10-01',6,6,95,'全面讲述算法，数据结构必备知识，内容全面与实际应用相结合的算法领域经典参考书','2018-05-01'),"
							+"('6','PPT设计思维','家庭与办公室用书','邵云蛟','邵云蛟','电子工业出版社','2016-10-01',8,8,56.7,'本书逻辑性强，内容丰富，从基础知识到提升技巧面面俱到，涵盖了PPT设计的方方面面','2018-05-01'),"

							//6本历史
							+"('7','中国近代史','历史','蒋廷黻','蒋廷黻','民主与建设出版社','2017-08-01',3,2,29,'蒋廷黻从事中国近代史的教学与研究长达十余年，搜罗原始材料，采用先进方法，开风气之先，为中国近代史的研究建立了科学的基础。本书是蒋廷黻中国近代史研究的经典之作，产生过广泛的影响','2018-06-01'),"
							+"('8','万历十五年','历史随笔','黄仁宇','黄仁宇','生活·读书·新知三联书店','2006-06-01',5,4,16.1,'作者以该年前后的史事件及生活在那个时代的人物为中心，抽丝剥茧，梳理了中国传统社会管理层面存在的种种问题，并在此基础上探索现代中国应当涉取的经验和教训','2018-06-01'),"
							+"('9','未来简史','世界通史','【以色列】尤瓦尔·赫拉利','林俊宏','中信出版社','2017-04-01',8,7,68,'讲述了从十万年前有生命迹象开始到21世纪资本、科技交织的人类发展史，见微知著、以小写大，让人类重新审视自己','2018-06-01'),"
							+"('10','知行合一 王阳明','历史普及读物','度阴山','度阴山','北京时代华文书局','2016-06-01',7,7,72,'本书是明代哲学宗师王阳明的论学语录和书信集，集中体现了王阳明心学的核心观点，是了解王阳明心学经典的入门必备书','2018-06-01'),"
							+"('11','天朝的崩溃','中国史','茅海建','茅海建','生活·读书·新知三联书店','2014-10-01',5,5,44.2,'本书对我国鸦片战争这段历史作了回顾，试图以全新的视角来解析这场颠覆清王朝的浩劫。作为一名求真的治史者，作者历数了清朝的军事力量、骤然而至的战争等内容，从一个侧面反映了鸦片战争对于清朝统治打击的致命性，是一部不可多得的史学论著','2018-06-01'),"
							+"('12','民国往事','近代史','萨沙','萨沙','北京联合出版公司','2015-10-01',2,2,7,'全书介绍了民国这个风范十足的时代，详细叙述了民国时期文化、社会的发展和重点历史人物的悲欢离合，再现了一个真实而多彩的民国时代','2018-06-01'),"


							//6本营销管理
							+"('13','我在阿里做运营','经营管理','芮曦（@小马鱼）','芮曦（@小马鱼）','电子工业出版社','2018-02-01',5,5,59,'解密鲜为人知的阿里运营精髓；展示在阿里大平台做运营的真实场景；《运营之光》作者黄有璨，《人人都是产品经理》作者苏杰，《斜杠青年》作者Susan Kuang联袂点赞','2018-07-01'),"
							+"('14','影响力','市场营销','【美】罗伯特·B.西奥迪尼（Robert B. Cialdini）','陈聪','北京联合出版公司','2016-09-01',6,6,33,' 在这本书中，心理学家罗伯特·B·西奥迪尼博士为我们解释了为什么有些人极具说服力，而我们总是容易上当受骗。隐藏在冲动地顺从他人行为背后的6大心理秘笈，正是这一切的根源，那些劝说高手们，总是熟练地运用它们，让我们就范','2018-07-01'),"
							+"('15','清单革命','市场营销','【美】阿图•葛文德（Atul Gawande）','李谷','北京联合出版公司','2017-05-01',4,4,39.5,'使用清单，就是为大脑搭建起一张“认知防护网”，它能够弥补人类与生俱来的认知缺陷，如记忆不完整或注意力不集中。作者在书中提出清单的4大行事原则：权力下放、简单至上、人为根本及持续改善。它们不是僵化的教条，而是实用的支持体系，将在复杂的世界中拯救你的生活','2018-07-01'),"
							+"('16','消费者行为心理学','市场营销','张意轩','张意轩','中国商业出版社','2014-12-01',4,4,22,'看似简单的消费行为背后其实是与人类复杂心理密切相关的。其实这就是消费行为心理学的内容，《消费者行为心理学》就是为你揭开这些消费行为中的小秘密而生','2018-07-01'),"
							+"('17','逆向管理:先行动后思考 ','管理类','【法】埃米尼亚·伊贝拉','王铮','北京联合出版公司','2018-04-01',6,6,48.5,'颠覆传统理念的管理新思维，管理者必读之书！联合利华CEO保罗·波尔曼等多位世界500强高管联袂推荐！全球50大管理思想家埃米尼亚·伊贝拉教授教你如何成为一名高效的管理者','2018-07-01'),"
							+"('18','文案创作与活动策划 从入门到精通','管理类','苏航','苏航','人民邮电出版社','2018-06-01',7,7,47.8,'本书围绕文案创作和活动策划主题，为读者提供拿来即用的实战技巧。为读者提供了实操性强的解决方案，并给出了丰富的实战案例。本书适合文案创作和活动策划人员，还适合电商、新媒体等行业从业人员','2018-07-01'),"

							//6本小说
						+"('19','活着','社科类','余华','余华','作家出版社','2012-08-01',5,5,15.3,'本书通过一位中国农民的苦难生活，讲述了人如何去承受巨大的苦难，讲述了眼泪的丰富和宽广，讲述了希望的不存在，讲述了人是为了活着本身而活着，用独特的表述方式，揭示了在困境中求生的理念，展现了生命的顽强与乐观，','2018-08-05'),"
							+"('20','人间失格','社会人性','【日本】太宰治','杨伟','作家出版社','2015-08-01',2,2,10,'描写主角从青少年到中年，为了逃避现实而不断沉沦，经历自我放逐、酗酒、自杀、用药物麻痹自己，终于一步步走向自我毁灭的悲剧，在自我否定的过程中，抒发自己内心深处的苦闷，以及渴望被爱的情愫','2018-08-05'),"
							+"('21','追风筝的人','世界名著','【美】卡勒德·胡赛尼(Khaled Hosseini)','李继红','上海人民出版社','2006-05-01',5,5,19.7,'小说残忍而又美丽，作者以温暖细腻的笔法勾勒人性的本质与救赎，读来令人荡气回肠，年少的童真，在经历了一系列生活的打击后，能否保持不变，作者心生疑惑并需求答案','2018-08-05'),"
							+"('22','你坏','中国近现代小说','大冰','大冰','湖南文艺出版社','2018-06-01',6,6,22.3,'本书脱胎于2013年的《他们的幸福》，加料回炉后，不留遗憾的完整版。为了她真正的淬火重生，大冰对她进行了大量重写和复原。抒发了作者对自由的向往和赞扬','2018-08-05'),"
							+"('23','三体','现代科幻小说','刘慈欣','刘慈欣','重庆出版社','2010-10-01',8,8,46.5,'《三体》的幻想源于经典物理中的三体问题，基于这样的科学事实，用大胆的想象和严谨的推断，在三体星系的行星中构建了一个外星文明形态，并描绘了该文明在如同不可捉摸的命运，数百次的毁灭和重生，当他与地球有了交集后，不同的文明之间迸发了一系列侵略与反抗的斗争...','2018-08-05'),"
							+"('24','马尔克斯：百年孤独','外国小说','【哥伦比亚】加西亚·马尔克斯(GarcíaMárquez)','唐伟杰','南海出版公司','2017-08-01',7,7,41.7,'《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪重要的经典文学巨著','2018-08-05'),"

							//6本传记
							+"('25','我们仨','文学自传','杨绛','杨绛','生活.读书.新知三联书店','2018-06-01',5,5,19.9,'杨绛以其一贯的慧心、独特的笔法，用梦境的形式讲述了抗战后几年中一家三口相依为命的情感体验。以平实感人的文字记录了自1935年伉俪二人赴英国留学，并在牛津喜得爱女，直至1998年钱先生逝世63年间这个家庭鲜为人知的坎坷历程','2018-8-20'),"
							+"('26','人类群星闪耀时','历史人物传记','【奥】斯蒂芬·茨威格','潘子立、高中甫','时代文艺出版社','2018-01-01',3,3,17.9,'《人类群星闪耀时》是历史上杰出的传记作家斯蒂芬·茨威格的传世之作，截取拿破仑、歌德、托尔斯泰、西塞罗、列宁、威尔逊等伟人的历史性时刻，以独特的视角，展开14个历史特写，刻画14位时代英雄，影响世界历史进程的14个精彩瞬间','2018-08-20'),"
							+"('27','胡适：四十自述','人物自传','胡适','胡适','民主与建设出版社','2015-05-01',4,4,19,'胡适先生回顾自己人生前四十年的经历，从他的信仰、母亲的订婚、慈母的教育、三岁入塾、叫局吃花酒到醉酒打巡捕、闭门读书考上庚款留美、至 “逼上梁山”的文学革命，完整地讲述了自己成长、学习与突破的根源与历程','2018-08-20'),"
							+"('28','邓小平传','人物传记','【英】理查德·伊文思（Richard Evans）','田山','国际文化出版公司','2013-02-01',6,6,35.8,'西方邓小平研究专家、英国前驻华大使理查德·伊文思（Richard Evans），与邓小平多次面对面接触，三十年心血力作，全方位揭秘鲜为人知的邓小平,他站在独特的立场，以平和的眼光看待并展示邓小平波澜壮阔的一生，也带着读者重温20世纪中国的跌宕历史','2018-08-20'),"
							+"('29','史蒂夫·乔布斯传','人物传记','【美】沃尔特·艾萨克森(Walter Isaacson)','刘越','中信出版社','2014-03-01',7,7,40.6,'《史蒂夫乔布斯传》是乔布斯授权的官方传记，由著名作家沃尔特·艾萨克森（Walter Isaacson）在过去两年与乔布斯面对面交流40多次、对乔布斯100多位家庭成员、朋友、竞争对手和同事的采访的基础上撰写而成，详尽描写了乔布斯的成长经历，创业生涯和打造苹果公司及其产品的全过程','2018-08-20'),"
							+"('30','居里夫人自传','人物自传','【法】玛丽·居里','陈晓卿','作家出版社','2015-10-01',4,4,14.2,'《居里夫人自传》共分两个部分：第一部分为居里夫人为自己撰写的自传。通过直白、坦诚的语言，真实记录了自己——一个普通女孩，通过刻苦努力、顽强奋斗走上科学之路的传奇一生。第二部分则是居里夫人为丈夫皮埃尔居里所写的传记。书中通过居里夫人的记叙、两人的书信、日记，以及他人对皮埃尔居里的评价等，展现出了皮埃尔居里的高尚人格魅力','2018-08-20')";

				String insertBorrowSqlStr ="INSERT INTO borrows(borrowId, userName, bookName, borrowDate, dueDate) VALUES"
						+"('201','林子珉','JAVA语言程序设计','2018-10-20','2018-11-20'),"
						+"('202','潘永佳','JAVA语言程序设计','2018-10-20','2018-11-20'),"
						+"('203','李桂鸿','中国近代史','2018-10-20','2018-11-20'),"
						+"('204','王明岱','Python编程 从入门到实践','2018-10-20','2018-11-20'),"
						+ "('205','潘永佳','Python编程 从入门到实践','2018-10-20','2018-11-20'),"
						+ "('206','李桂鸿','C Primer Plus','2018-10-20','2018-11-20'),"
						+ "('207','林子珉','C Primer Plus','2018-10-20','2018-11-20'),"
						+ "('208','徐斌','万历十五年','2018-10-20','2018-11-20'),"
						+ "('209','王明岱','未来简史','2018-10-20','2018-11-20')";
				con.setAutoCommit(false);// 打开事务，以保证所有的数据库操作执行都成功或都失败。
				
				processUpdateStatement(createUserTableSqlStr);
				processUpdateStatement(createBookTableSqlStr);
				processUpdateStatement(createBorrowTableSqlStr);
				processUpdateStatement(createCookieSqlStr);
				processUpdateStatement(insertUserSqlStr);
				processUpdateStatement(insertBookSqlStr);
				processUpdateStatement(insertBorrowSqlStr);
				//batchInsertStudents();  //批量添加用户信息
				
				con.commit();// 提交事务
				
				con.setAutoCommit(true);// 关闭事务
				return 1;

			} catch (SQLException bue) {
				try {
					con.rollback();// 执行出错，回滚事务
					printSQLException((SQLException) bue);
				} catch (SQLException se) {
					printSQLException(se);
				}
				return -1;// 创建不成功，返回-1。
			} catch (ClassNotFoundException e) {
				System.err.println("JDBC Driver " + driver + " not found in CLASSPATH");
				return -1;// 创建不成功，返回-1。
			} finally {
				closeConnection(con);
			}
		}
		return 0;// 数据库已经存在，返回0，不做任何操作.
	}
	
	/**
	 * 该方法判断数据库是否已经存在。
	 * 
	 * @return
	 */
	public static boolean dbExists() {
		boolean exists = false;
		Connection con = getConnection();
		if (con != null)// 如果con不为null,则连接成功，表明数据库已经存在，设置exists变量为true
			exists = true;
		closeConnection(con);
		return (exists);
	}

	
	/**
	 * 该方法创建到数据库的连接并返回该连接。注：使用完连接后，要关闭连接，以节省系统资源。
	 * 
	 * @return 返回所创新的到数据库的新连接。
	 */
	public static Connection getConnection() {
		Connection con;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url + dbName);

		} catch (Exception e) {
			return null;
		}
		return con;
	}
	
	
	/**
	 * 该方法关闭传递进行的数据库连接
	 * 
	 * @param con
	 *            要关闭的数据库连接
	 */
	public static void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 该方法以更新的方式执行传递过来的sql串。
	 * 
	 * @param sql
	 *            要执行的sql串。
	 * @return 受影响的记录数。返回-1代表执行sql语句失败。
	 * @throws SQLException
	 *             抛出的SQL异常
	 */
	private static int processUpdateStatement(String sql) throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			Statement stmt = con.createStatement();
			int affectedRows = stmt.executeUpdate(sql);
			closeConnection(con);
			return affectedRows;
		}
		return -1;
	}
	
	
	/**
	 * 该方法以查询的方式执行传递过来的sql串。
	 * 
	 * @param 要执行的sql串。
	 * @return 返回查询结果记录集。返回null代表查询失败
	 * @throws SQLException
	 *             抛出的SQL异常
	 */
	private static ResultSet processQueryStatement(String sql) throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			stmt.close();
			return rs;
		}
		return null;
	}
	
	
	/**
	 * 打印SQL异常信息
	 * 
	 * @param e
	 *            要打印的SQL异常
	 */
	private static void printSQLException(SQLException e) {
		while (e != null) {
			System.out.print("SQLException: State:   " + e.getSQLState());
			System.out.println("Severity: " + e.getErrorCode());
			System.out.println(e.getMessage());
			e = e.getNextException();
		}
	}
}
