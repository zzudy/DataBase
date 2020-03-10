import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String server = "116.36.18.83:53306"; // MySQL 서버 주소
        String database = "Water_dispenser"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "votmdnjem"; // MySQL 서버 비밀번호
        String second;
        String test;

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            //System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        try {

            Statement stmt = null;
            assert con != null;
            stmt = con.createStatement();


            String qry = "INSERT INTO company(id,name) VALUES(1,'LG')";
            String getqry = "SELECT * FROM company";
            System.out.println("정수기 가격 비교 프로그램\n");
            while(true) {
                System.out.println("\n"+ "1)제품이름 검색  2)모델명 검색  3)기능 선택 4)관리자모드 5)종료");
                int n = Integer.parseInt(scan.nextLine());
                if(n==1) { // model_name
                    System.out.println("제품이름을 입력해주세요  초기화면은 -");
                    String input =scan.nextLine();
                    if(input.equals("-")) continue;
                    String get_water_d ="SELECT * FROM show_info where wname like "
                            +"\'%" +input+ "%\'";
                    rs = stmt.executeQuery(get_water_d);
                    while(rs.next()){
                        String name = rs.getString("wname");
                        String company = rs.getString("name");
                        String model = rs.getString("model_name");

                        String price_3 = rs.getString("price3");
                        String price_4 = rs.getString("price4");
                        String price_5 = rs.getString("price5");
                        String function ="";
                        String[] arr = {"냉수", "온수", "직수", "얼음", "다기능"};
                        String[] arr1 = {"cold", "hot", "direct", "ice", "multi_function"};
                        for(int i=0; i<5; i++) {
                            test =rs.getString(arr1[i]);
                            if(test.equals("1")) {
                                function+= arr[i]+ ", ";
                            }
                        }
                        function = function.substring(0,function.length()-2);
                        System.out.println("____________________________________________________________________________");
                        System.out.printf("정수기 이름: %15s     ",name);
                        System.out.printf("회사: %10s\n", company);
                        System.out.printf("모델명: %20s     ", model);
                        System.out.printf("기능: %s\n" , function);
                        System.out.println("3년 월 렌탈비: "+ price_3 + "원  4년 월 렌탈비: "+ price_4+"원  5년 월 렌탈비: "+ price_5+"원");
                        System.out.println("____________________________________________________________________________");

                        get_water_d ="SELECT * FROM show_review where model_name =\"" +model+ "\"";
                        Statement stmt1=con.createStatement();
                        ResultSet rs1 = stmt1.executeQuery(get_water_d);
                        int cnt=1;
                        while(rs1.next()){
                            String review = rs1.getString("r_text");
                            String star = rs1.getString("star");
                            System.out.println("리뷰 "+cnt+++": "+ review);
                            for(int i=0; i<Integer.parseInt(star); i++) {
                                System.out.print("★");
                            }
                            System.out.println("");
                        }
                    }
                    System.out.println("");
                    System.out.println("____________________________________________________________________________");

                }
                else if(n==2) { // model_name
                    System.out.println("모델명을 입력해주세요  초기화면은 -");
                    String input =scan.nextLine();
                    if(input.equals("-")) continue;
                    String get_water_d ="SELECT * FROM show_info where model_name like "
                            +"\'%" +input+ "%\'";
                    rs = stmt.executeQuery(get_water_d);
                    while(rs.next()){
                        String name = rs.getString("wname");
                        String company = rs.getString("name");
                        String model = rs.getString("model_name");

                        String price_3 = rs.getString("price3");
                        String price_4 = rs.getString("price4");
                        String price_5 = rs.getString("price5");
                        String function ="";
                        String[] arr = {"냉수", "온수", "직수", "얼음", "다기능"};
                        String[] arr1 = {"cold", "hot", "direct", "ice", "multi_function"};
                        for(int i=0; i<5; i++) {
                            test =rs.getString(arr1[i]);
                            if(test.equals("1")) {
                                function+= arr[i]+ ", ";


                            }

                        }
                        function = function.substring(0,function.length()-2);
                        System.out.println("____________________________________________________________________________");
                        System.out.printf("정수기 이름: %15s     ",name);
                        System.out.printf("회사: %10s\n", company);
                        System.out.printf("모델명: %20s     ", model);
                        System.out.printf("기능: %s\n" , function);
                        System.out.println("3년 월 렌탈비: "+ price_3 + "원  4년 월 렌탈비: "+ price_4+"원  5년 월 렌탈비: "+ price_5+"원");
                        System.out.println("____________________________________________________________________________");

                        get_water_d ="SELECT * FROM show_review where model_name =\"" +model+ "\"";
                        Statement stmt1=con.createStatement();
                        ResultSet rs1 = stmt1.executeQuery(get_water_d);
                        int cnt=1;
                        while(rs1.next()){
                            String review = rs1.getString("r_text");
                            String star = rs1.getString("star");
                            System.out.println("리뷰 "+cnt+++": "+ review);
                            for(int i=0; i<Integer.parseInt(star); i++) {
                                System.out.print("★");
                            }
                            System.out.println("");
                        }
                    }
                    System.out.println("");
                    System.out.println("____________________________________________________________________________");

                }
                else if(n==3) {
                    String[] s= new String[]{"", "cold=\"1\" and ", "hot=\"1\" and ", "direct=\"1\" and ", "ice=\"1\" and ", "multi_function=\"1\" and "};
                    System.out.println("1)냉수 2)온수 3)직수 4)얼음 5)다기능 6)초기화면");
                    second = scan.nextLine();
                    if(second.equals("6")) continue;
                    String[] a = second.split(",");
                    int [] l = new int [7];
                    String cond = "";
                    for(int i=0;i<a.length;i++) {
                        cond = cond.concat(s[Integer.parseInt(a[i].trim())]);
                    }
                    qry =  "SELECT * FROM show_info where ";
                    qry = qry.concat(cond);
                    qry = qry.substring(0,qry.length()-5);
                    qry = qry.concat(";");
                    //   System.out.println(qry);
                    String model_name="";
                    rs = stmt.executeQuery(qry);
                    String r_qry="";
                    while(rs.next()) {
                        String model = rs.getString("model_name");
                        String name = rs.getString("wname");
                        String company = rs.getString("name");
                        int price_3 = rs.getInt("price3");
                        int price_4 = rs.getInt("price4");
                        int price_5 = rs.getInt("price5");
                        String function ="";
                        String[] arr = {"냉수", "온수", "직수", "얼음", "다기능"};
                        String[] arr1 = {"cold", "hot", "direct", "ice", "multi_function"};
                        for(int i=0; i<5; i++) {
                            test =rs.getString(arr1[i]);
                            if(test.equals("1")) {
                                function+= arr[i]+ ", ";
                            }
                        }
                        function = function.substring(0,function.length()-2);
                        System.out.println("____________________________________________________________________________");
                        System.out.printf("정수기 이름: %15s     ",name);
                        System.out.printf("회사: %10s\n", company);
                        System.out.printf("모델명: %20s     ", model);
                        System.out.printf("기능: %s\n" , function);
                        System.out.println("3년 월 렌탈비: "+ price_3 + "원  4년 월 렌탈비: "+ price_4+"원  5년 월 렌탈비: "+ price_5+"원");
                        System.out.println("____________________________________________________________________________");
                        r_qry ="SELECT * FROM show_review where model_name =\"" +model+ "\"";
                        Statement stmt1=con.createStatement();
                        ResultSet rs1 = stmt1.executeQuery(r_qry);
                        int cnt=1;
                        while(rs1.next()){
                            String review = rs1.getString("r_text");
                            String star = rs1.getString("star");
                            System.out.println("리뷰 "+cnt+++": "+ review);
                            for(int i=0; i<Integer.parseInt(star); i++) {
                                System.out.print("★");
                            }
                            System.out.println("");
                        }
                    }
                    System.out.println("");
                    System.out.println("____________________________________________________________________________");
                }
                else if(n==4) {
                    int select;
                    System.out.println("관리자모드");
                    //trigger 작성
                    String model_name;
                    String wname;
                    String name;
                    String function;
                    String price;
                    System.out.println("1)추가 2)삭제 3)업데이트 4)초기화면");
                    select = Integer.parseInt(scan.nextLine());
                    if(select == 4) continue;
                    if(select == 1) { // 정수기 추가
                        System.out.println("추가할 정수기의 이름을 입력하세요 : ");
                        wname = scan.nextLine();
                        System.out.println("추가할 정수기의 모델명을 입력하세요 : ");
                        model_name = scan.nextLine();
                        System.out.print("추가할 정수기의 업체ID을 입력하세요 :   ");
                        qry = "select * from company";
                        rs = stmt.executeQuery(qry);
                        while(rs.next())
                            System.out.printf("%d %s  ",rs.getInt("id"),rs.getString("name"));
                        System.out.println("");
                        name = scan.nextLine();

                        do{
                            System.out.println("추가할 정수기의 기능을 입력하세요 : ");
                            System.out.println("1)냉수 2)온수 3)직수 4)얼음 5)다기능");
                            second = scan.nextLine();
                        }while(!second.matches("^[0-9, ]+$"));
                        String cold = "0" , hot = "0", direct = "0", ice = "0", multi_function = "0";
                        String[] a = second.split(",");
                        String cond = "";
                        String[] arr = {"cold", "hot", "direct", "ice", "multi_function"};
                        for(int i=0;i<a.length;i++) {
                            cond = cond.concat(arr[Integer.parseInt(a[i].trim())]);
                        }
                        if(cond.contains("cold")){
                            cold = "1";
                        }
                        if(cond.contains("hot")){
                            hot = "1";
                        }
                        if(cond.contains("direct")){
                            direct = "1";
                        }
                        if(cond.contains("ice")){
                            ice = "1";
                        }
                        if(cond.contains("multi_function")){
                            multi_function = "1";
                        }
                        System.out.println("추가할 정수기의 3년 월 렌탈가격을 입력하세요 : ");
                        price = scan.nextLine();

                        qry = "INSERT INTO water_dispenser(wname, company, model_name) VALUES(\"" + wname +
                                "\", \""+ name + "\", \"" + model_name+ "\")";
                        String qry1 = "INSERT INTO rental_Info(model_name, price3, price4, price5) "
                                + "VALUES(\"" + model_name + "\", \""+ price + "\", null, null)";
                        String qry2 = "INSERT INTO functions(model_name, cold, hot, direct, ice, multi_function) VALUES"
                                + "(\"" + model_name+"\", \""+ cold +"\", \"" + hot +"\", \""+direct +"\", \""+ ice +"\", \""+ multi_function+"\")";
                        stmt.executeUpdate(qry);
                        stmt.executeUpdate(qry1);
                        stmt.executeUpdate(qry2);
                        String get_water_d ="SELECT * FROM show_info where model_name= "
                                +"\"" +model_name+ "\"";
                        rs = stmt.executeQuery(get_water_d);
                        while(rs.next()){
                            name = rs.getString("wname");
                            String company = rs.getString("name");
                            String model = rs.getString("model_name");

                            String price_3 = rs.getString("price3");
                            String price_4 = rs.getString("price4");
                            String price_5 = rs.getString("price5");
                            String function1 ="";
                            String[] arr1 = {"냉수", "온수", "직수", "얼음", "다기능"};
                            for(int i=0; i<5; i++) {
                                test =rs.getString(arr[i]);
                                if(test.equals("1")) {
                                    function1+= arr1[i]+ ", ";
                                }
                            }
                            if(function1.length()>2)
                                function1 =function1.substring(0,function1.length()-2);
                            System.out.println("___________________________________________________________________________________________________________________________");
                            System.out.println("정수기 이름: "+name +" "
                                    + "   회사: "+company +"      모델명: "+model
                                    + "      기능: " + function1);
                            System.out.println("      3년 월 렌탈비: "+ price_3 + "원  4년 월 렌탈비: "+ price_4+"원  5년 월 렌탈비: "+ price_5+"원");
                            System.out.println("___________________________________________________________________________________________________________________________");
                        }
                    }
                    else if(select == 2) { //정수기 삭제
                        System.out.println("삭제할 정수기의 모델명을 입력하세요 : ");
                        qry = "select model_name from water_dispenser";
                        rs = stmt.executeQuery(qry);

                        int cnt=0;
                        while(rs.next()) {
                            cnt++;
                            if (cnt % 4 == 0) System.out.println("");
                            System.out.printf("모델명: %s     ", rs.getString("model_name"));
                        }
                        model_name = scan.nextLine();
                        qry = "DELETE from water_dispenser WHERE model_name = \"" + model_name + "\"";
                        if(stmt.executeUpdate(qry)>0)
                            System.out.println(model_name+"이(가) 삭제되었습니다.");
                        else System.out.println(model_name+"을(를) 찾을 수 없서 삭제하지 못했습니다.");
                    }
                    else if(select == 3) { //정수기 업데이트
                        System.out.println("업데이트할 정수기의 모델명을 입력하세요 : ");
                        qry = "select model_name from water_dispenser";
                        rs = stmt.executeQuery(qry);
                        int cnt=0;
                        while(rs.next()) {
                            cnt++;
                            if (cnt % 4 == 0) System.out.println("");
                            System.out.printf("모델명: %s     ", rs.getString("model_name"));
                        }
                        model_name = scan.nextLine();
                        System.out.println("바뀐 가격을 입력하세요 : ");
                        price = scan.nextLine();
                        qry = "UPDATE rental_Info set price3 = \"" + price + "\""
                                +"WHERE rental_Info.model_name = \"" + model_name + "\"";
                        stmt.executeUpdate(qry);
                    }
                }

                else{
                    System.out.println("종료");
                    break;
                }

            }



//            System.out.println(n);

        } catch( Exception e ) {
            System.err.println(e);
        }

        // 3.해제
        try {
            if(con != null) {
                if(con!=null)con.close();
                if(rs!=null)rs.close();
                if(pstmt!=null)pstmt.close();
            }
        } catch (SQLException e) {}
    }

}