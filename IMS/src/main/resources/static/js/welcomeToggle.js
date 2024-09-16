/******* @Author SWATI *******/

const mobileScreen = window.matchMedia("(max-width: 600px )");
$(document).ready(function () {
   
    $(".menu-toggle").click(function () {
        if (mobileScreen.matches) {
            $(".dashboard-nav").toggleClass("mobile-show");
        } else {
            $(".dashboard").toggleClass("dashboard-compact");
        }
    });
});



function confirmLogout() {
    // Display a SweetAlert confirmation dialog
    Swal.fire({
      title: 'Logout Confirmation',
      text: 'Are you sure you want to logout?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, logout!'
    }).then((result) => {
      if (result.isConfirmed) {
        //  clicked "Yes, logout!" - perform the logout
        logout();
      }
    });
  }

  function logout() {
    try {
      window.location.href = '/IMS/';
    } catch (error) {
      console.error("Logout error:", error);
      Swal.fire({
        icon: 'error',
        title: 'Logout Error',
        text: 'An error occurred during logout. Please try again.',
      });
    }
  }
  
  /*-----------------------------------------IFRAME RESPONSIVE FUNCTION-------------------------------------------------------------*/
  
  
   function adjustIframeHeight() {
    var iframe = document.getElementById('adminRight');
    if (iframe) {
      var windowHeight = window.innerHeight ||
        document.documentElement.clientHeight ||
        document.body.clientHeight;

      
      iframe.style.height = (windowHeight-55) + 'px';
    }
  }

  
  adjustIframeHeight();
  window.addEventListener('resize', adjustIframeHeight);

  /**************** DYNAMIC MENU-BAR ******************/

  document.addEventListener("DOMContentLoaded", function() {
      var menuItems = document.querySelectorAll('.with-submenu');

      menuItems.forEach(function(item) {
          item.addEventListener('click', function(e) {
              e.preventDefault();
              var submenu = this.nextElementSibling;

              console.log("submenu:", submenu);

              if (submenu) {
                  if (submenu.style.display === 'none') {
                      submenu.style.display = 'block';
                  } else {
                      submenu.style.display = 'none';
                  }
              }
          });
      });
  });
