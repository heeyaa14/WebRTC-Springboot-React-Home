let index = {
	// 변수는 let 쓰고 ,함수의 이름이 없어도 실행될수밖에 없는 영역에 ()=> 씀
	init : function(){
		// 이벤트를 바인딩한다.
		
		// 1.리스너
		$("#btn-save").on("click",()=>{
			// 콜백 스택
			this.save();
		});
		
		// 2.리스너
		$("#btn-login").on("click",()=>{
			this.login();
		});
	},
	
	// 이렇게 설계하면 장점 : 버튼 리스너를 새로 만들고 밑에서 처리하면됨
	save : function() {
		let data = {
                id:$("#id").val(),
                password:$("#password").val(),
                username:$("#username").val(),
                email:$("#email").val(),
                phone:$("#phone").val(),
                gender:$("#gender").val(),
        };
        
		// fetch쓰면 jquery안써도 됨
		$.ajax({
			type :"POST",
			url: "/join",
			// 자바스크립트 오브젝트를 JSON String으로 바꾼것
			data : JSON.stringify(data),
			// 스프링이 메세지 컨버터가 작동해서 requestBody를 붙여야함
			// 그러기 위해서는 스프링에게 컨텍스트 타입을 알려줘야한다. 그래야만 오브젝트로 변환해줌
			contentType : "application/json; charset=utf-8",
			// 응답 받는 타입 설정
			dataType : "json"
		}).done((resp)=>{
			alert("회원가입 성공");
			location.href="/";
			console.log(resp);
		}).fail(function(error){
			alert("회원가입 실패");
			console.log(error);
		})
		// 자바스크립트오브젝트 => 제이슨 스트링
		// JSON.stringify(자바스크립트오브젝트);
		// 제이슨 스트링 => 자바스크립트 오브젝트
        // JSON.parse(제이슨스트링);
        
	},
	
	login : function() {
		let data = {
				id:$("#id").val(),
				password:$("#password").val()
        };
        
        /*
		// fetch쓰면 jquery안써도 됨
		// login 방식은 get인데 예외로 post이용
		$.ajax({
			type :"POST",
			url: "/auth/loginProc",
			// 자바스크립트 오브젝트를 JSON String으로 바꾼것
			data : JSON.stringify(data),
			// 스프링이 메세지 컨버터가 작동해서 requestBody를 붙여야함
			// 그러기 위해서는 스프링에게 컨텍스트 타입을 알려줘야한다. 그래야만 오브젝트로 변환해줌
			contentType : "application/json; charset=utf-8",
			// 응답 받는 타입 설정
			dataType : "json"
		}).done((resp)=>{
			if(resp.statusCode ==1){
				alert("로그인 성공");
				location.href="/";
			}else{
				alert("로그인 실패");
				console.log(resp);
			}
		}).fail(function(error){
			alert("로그인 실패");
			console.log(error);
        })
        */
	},
	
}

index.init();