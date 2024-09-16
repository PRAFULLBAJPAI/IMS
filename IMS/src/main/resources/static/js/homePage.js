/**
 * 
 */

// main toggle js
let toggle = document.querySelector('.toggle');
let navigation = document.querySelector('.navigation');
let main = document.querySelector('.main');

toggle.onclick = function(){
	navigation.classList.toggle('active');
	main.classList.toggle('active');
}


  // Add hover class in selected div list item
  let list = document.querySelectorAll('.navigation li');
  function activeLink(){
  	list.forEach((item)=>
  	item.classList.remove('hovered'));
  	this.classList.add('hovered');
  }
  list.forEach((item)=>
  item.addEventListener('mouseover',activeLink))

  
 /*-------------------------CHART JS---------------------------- */

const ctx = document.getElementById('myChart');
const employeeData = document.getElementById('employeeData');
// Define colors for each label
const barColors = ['rgba(181, 5, 5,0.7)', 'rgba(11, 40, 204,0.7)', 'rgba(30, 135, 33,0.7)', 'rgba(227, 227, 16, 0.7)', 'rgba(171, 41, 194, 0.7)', 'rgba(201, 109, 4, 0.7)'];

/*-------------------------CHART JS---------------------------- */

const chart1 = new Chart(ctx, {
  type: 'polarArea',
  data: {
    labels: ['Noida', 'Chennai', 'Kanpur', 'Pune', 'Mumbai', 'Gurugram'],
    datasets: [{
      label: 'Complaint Registered',
      data: [20, 14, 23, 11, 18, 25],
      borderWidth: 1,
      backgroundColor: barColors
    }]
  },
  options: {
    responsive: true,
  }
});

const chart2 = new Chart(employeeData, {
  type: 'bar',
  data: {
    labels: ['Divyanshu', 'Suyash', 'Sireesha', 'Swati', 'Akansha', 'Amisha'],
    datasets: [{
      label: 'Employee Handling Complaints',
      data: [20, 14, 23, 11, 18, 25],
      borderWidth: 1,
      backgroundColor: 'rgba(191, 49, 49, 0.5)',
      borderColor: 'rgb(191, 49, 49)'
    }]
  },
  options: {
    responsive: true,
    plugins: {
      tooltip: {
        callbacks: {
          label: function(context) {
            let label = 'Branch: ' + chart1.data.labels[context.dataIndex] + '\n';
            label += '\nComplaints: ' + context.parsed.y;
            return label;
          }
        }
      }
    }
  }
});

