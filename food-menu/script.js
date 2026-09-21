```javascript
const foods=[
  {name:"Cheese Burger",cat:"Burger",price:149,img:"🍔"},
  {name:"Chicken Burger",cat:"Burger",price:179,img:"🍔"},
  {name:"Margherita Pizza",cat:"Pizza",price:249,img:"🍕"},
  {name:"Farmhouse Pizza",cat:"Pizza",price:299,img:"🍕"},
  {name:"Cold Coffee",cat:"Drinks",price:99,img:"🥤"},
  {name:"Lemon Soda",cat:"Drinks",price:79,img:"🍋"},
  {name:"Brownie",cat:"Dessert",price:109,img:"🍫"},
  {name:"Ice Cream",cat:"Dessert",price:89,img:"🍨"}
];

let cart=[];
let category="All";

const foodList=document.getElementById("foodList");
const search=document.getElementById("search");
const count=document.getElementById("count");
const cartBox=document.getElementById("cart");
const cartItems=document.getElementById("cartItems");
const total=document.getElementById("total");

function showFoods(){
  const text=search.value.toLowerCase();

  const result=foods.filter(f=>
    (category==="All"||f.cat===category) &&
    f.name.toLowerCase().includes(text)
  );

  foodList.innerHTML=result.map((f,i)=>`
    <div class="card">
      <div class="food-img">${f.img}</div>
      <h3>${f.name}</h3>
      <p>Fresh and delicious ${f.cat.toLowerCase()}.</p>
      <div class="price">
        <strong>₹${f.price}</strong>
        <button onclick="addCart(${foods.indexOf(f)})">Add</button>
      </div>
    </div>
  `).join("");
}

function addCart(i){
  cart.push(foods[i]);
  updateCart();
}

function updateCart(){
  count.textContent=cart.length;

  cartItems.innerHTML=cart.map((f,i)=>`
    <div class="cart-item">
      <span>${f.name}</span>
      <span>₹${f.price} <button onclick="removeItem(${i})">×</button></span>
    </div>
  `).join("");

  total.textContent=cart.reduce((sum,f)=>sum+f.price,0);
}

function removeItem(i){
  cart.splice(i,1);
  updateCart();
}

document.querySelectorAll(".categories button").forEach(btn=>{
  btn.addEventListener("click",()=>{
    document.querySelector(".categories .active").classList.remove("active");
    btn.classList.add("active");
    category=btn.dataset.cat;
    showFoods();
  });
});

search.addEventListener("input",showFoods);

document.getElementById("cartBtn").onclick=()=>{
  cartBox.style.display="block";
};

document.getElementById("close").onclick=()=>{
  cartBox.style.display="none";
};

document.getElementById("order").onclick=()=>{
  alert("Order placed successfully!");
  cart=[];
  updateCart();
  cartBox.style.display="none";
};

showFoods();
```
