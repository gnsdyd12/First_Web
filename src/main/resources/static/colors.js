function getCurrentTheme() {
  const cookie = document.cookie;
  let theme = cookie.split("; ").map((v) => v.split("=")).find((ck) => ck[0] === "theme");

  console.log(theme)
  if(typeof theme === 'undefined') {
    document.cookie = "theme=day";
    return "day";
  } else {
    console.log(theme[1])
    return theme[1];
  }
}

function setTheme() {
  const theme = getCurrentTheme();
  console.log(theme);
  if(theme === "night")  {
    document.body.classList.add("theme-dark");
    document.cookie = "theme=day";
  } else {
    document.body.classList.remove("theme-dark");
    document.cookie = "theme=night";
  }
}


const Links={
  LinkssetColor:function(color){
    // var alist = document.querySelectorAll('a');
    // var i=0;
    // while(i<alist.length){
    //   console.log(alist[i]);
    //   alist[i].style.color=color;
    //   i=i+1;
    // }
      $('a').css('color', color);
  }
}

const Body= {
  setColor:function(color){
    //document.querySelector('body').style.color=color;
      $('body').css('color', color);
  },
  setBackgroundColor:function(color){
    //document.querySelector('body').style.backgroundColor=color;
      $('body').css('backgroundColor', color);
  }
}

function nightDayHandler(self){
    if(self.value === 'night'){
      document.body.classList.add("theme-dark")
      // Body.setBackgroundColor('black');
      // Body.setColor('white');
      self.value = 'day';
       Links.LinkssetColor('yellow');

    }
    else{
      document.body.classList.remove("theme-dark")
      // Body.setBackgroundColor('white');
      // Body.setColor('black');
      self.value = 'night';
       Links.LinkssetColor('blue');
    }

}

function loginHandler(self){
  if(self.value === 'Login'){
    googleAuth.signIn().then(function(){
      console.log('googleAuth.signIn()');
      checkLoginStatus();
    });
  }else{
    googleAuth.signOut().then(function(){
      console.log('googleAuth.signOut()');
      checkLoginStatus();
    });
  }
}
function checkLoginStatus(){
  var loginBtn = document.querySelector('#loginBtn');
  var nameTxt = document.querySelector('#name');
  if(googleAuth.isSignedIn.get()){
    console.log('logined');
    loginBtn.value ='Logout';
    var profile = googleAuth.currentUser.get().getBasicProfile();
    console.log(profile.getName());
    nameTxt.innerHTML = 'Welcome <strong>'+profile.getName()+ '</strong> ';
  } else{
    console.log('logoutted');
    loginBtn.value = 'Login';
    nameTxt.innerHTML='';
  }
}
function init(){
  console.log("init");
  gapi.load('auth2', function() {
    console.log("auth2");
    window.googleAuth = gapi.auth2.init({
      client_id:"236385600372-fsq9do4ehbamgcoe464ec27af6f1tuvt.apps.googleusercontent.com"
    })
    googleAuth.then(function(){
      console.log('googleAuth success');
      checkLoginStatus();
    }, function(){
      console.log('googleAuth fail');
    })
  });
}




