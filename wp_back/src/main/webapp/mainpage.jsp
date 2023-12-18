<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team up</title>
<meta charset="utf-8" />
        <link rel="stylesheet" href="./styles/mainpage.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A300%2C400%2C600%2C700%2C800"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Inter%3A300%2C400%2C600%2C700%2C800"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Orelega+One%3A400"/>
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Poppins'>
        <script>
            window.onload = function() {
                // 로그인 링크에 클릭 이벤트 리스너를 추가
                document.getElementById('loginLink').addEventListener('click', function(e) {
                    e.preventDefault();
                    document.getElementById('loginModal').style.display = 'block';
                });
                
                // 닫기 버튼에 클릭 이벤트 리스너를 추가
                document.getElementById('closeModal').addEventListener('click', function() {
                    document.getElementById('loginModal').style.display = 'none';
                });
            
                // 모달 창 외부를 클릭하면 모달 창을 닫음
                window.addEventListener('click', function(event) {
                    if (event.target == document.getElementById('loginModal')) {
                        document.getElementById('loginModal').style.display = 'none';
                    }
                });

                // 회원가입 링크에 클릭 이벤트 리스너를 추가
                document.getElementById('signupLink').addEventListener('click', function(e) {
                    e.preventDefault();
                    document.getElementById('signupModal').style.display = 'block';
                });
                
                // 닫기 버튼에 클릭 이벤트 리스너를 추가
                document.getElementById('closeModal').addEventListener('click', function() {
                    document.getElementById('signupModal').style.display = 'none';
                });

                // 모달 창 외부를 클릭하면 모달 창을 닫음
                window.addEventListener('click', function(event) {
                    if (event.target == document.getElementById('signupModal')) {
                        document.getElementById('signupModal').style.display = 'none';
                    }
                });
            }
            </script>
</head>
<body>

	<div class="page">
            <div class="navbar">
                <div class="navbar-left">
                <img id="logo_image"src="./assets/logo.png" alt="Logo">
                <span class="logo_word">Team up</span>
                </div>
                <div class="navbar-right">
                <a href="MakeTeam.jsp">파티 만들기</a>
                <a href="FindTeam_search.jsp">파티 찾기</a>
                <!-- 로그인 링크 -->
                <a href="#" id="loginLink">로그인</a>

                <!-- 모달 창 -->
                <div id="loginModal" class="modal">
                    <div class="modal-content">
                        <span id="closeModal" class="close">&times;</span>
                        <h2 style="font-family:'poppins'">Welcome to</h2><h2 class="logo_word">Team Up</h2><h2 style="font-family:'poppins'">!</h2>
                        <h1 style="font-family:'poppins'">Sign in</h1>
                        <br>
                        <br>
                        <form action="login.jsp" method="post" class="inputform">
                            <label for="username">아이디</label>
                            <br>
                            <input type="text" id="username" name="id" placeholder="Enter your user name" style="border-radius: 5px; height: 50px">
                            <br>
                            <label for="password">비밀번호</label>
                            <br>
                            <input type="password" id="password" name="password" placeholder="Enter your password" style="border-radius: 5px; height: 50px">
                            <br>
                            <input type="submit" value="로그인" style="border-radius: 5px; height: 50px">
                            <p>계정이 없으신가요? 회원가입</p>
                        </form>
                    </div>
                </div>
                <a href="#" id="signupLink">회원가입</a>

                <!-- 모달 창 -->
                <div id="signupModal" class="modal">
                    <div class="modal-content">
                        <span id="closeModal" class="close">&times;</span>
                        <h2 style="font-family:'poppins'">Welcome to</h2><h2 class="logo_word">Team Up</h2><h2 style="font-family:'poppins'">!</h2>
                        <h1 style="font-family:'poppins'">Sign up</h1>
                        <br>
                        <br>
                        <form action="register.jsp" method="post" class="inputform">
                            <label for="username">아이디</label>
                            <br>
                            <input type="text" id="username" name="username" placeholder="Enter your user name" style="border-radius: 5px; height: 50px">
                            <br>
                            <label for="phone">전화번호</label>
                            <br>
                            <input type="text" id="phone" name="phone" placeholder="Enter your phone number" style="border-radius: 5px; height: 50px">
                            <br>
                            <label for="password">비밀번호</label>
                            <br>
                            <input type="password" id="password" name="password" placeholder="Enter your password" style="border-radius: 5px; height: 50px">
                            <br>
                            <label for="confirmPassword">비밀번호 확인</label>
                            <br>
                            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" style="border-radius: 5px; height: 50px">
                            <br>
                            <input type="submit" value="회원가입" style="border-radius: 5px; height: 50px">
                            <p>이미 계정이 있으신가요? 로그인</p>
                        </form>
                    </div>
                </div>
                <img id="contact_us" src="./assets/contactus.png" alt="Contact Us">
                </div>
            </div>
            <div class="main" style="display: flex;">
                <div class="wrapper">
                  <div class="header-text-Vay">
                    <p class="h1-primary-text">
                      <span>Great Product is</span>   
                      <br> 
                      <span>built by great teams </span>
                    </p>
                    <p class="slogan">
                        <span>We help build and manage a team of world-class developers</span>
                        <br>
                        <span>to bring your vision to life</span>
                    </p>
                    
                  </div>
                  <img id="makepartynow" src="./assets/makepartynow.png" alt="makepartynow">
                </div>
                <img id="people" src="./assets/people.png"/>
              </div>
              
        </div>
</body>
</html>