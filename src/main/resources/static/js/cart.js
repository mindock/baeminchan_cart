class Cart {
    constructor() {
        this.cartList = {};
        this.getCartList();
        $("#products").addEventListener("click", this.productsClickHandler.bind(this));
    }
    initCartNumber(response) {
        response.json().then(cart => {
            this.cartList = cart.products;
            const cartSize = Object.keys(this.cartList).length;
            if(cartSize !== 0) {
                $("#cart_display_exist").style.display = "";
                $("#cart_display_none").style.display = "none";
                $("#basket-counter").innerText = Object.keys(this.cartList).length;
            }
        })
    }
    getCartList() {
        fetchManager({
            url: "/api/cart",
            method: "GET",
            headers: {"content-type": "application/json"},
            callback: this.initCartNumber.bind(this)
        })
    }
    accountChange(evt) {
        event.preventDefault();

        const target = evt.target;
        const accountInput =  target.closest(".prd_account").querySelector(".buy_cnt");
        //evt.target.parentElement.previousElementSibling.firstElementChild
        if(target.className === "up") {
            accountInput.value++;
            return;
        }
        if(accountInput.value === "1") {
            return;
        }
        accountInput.value--;
    }
    displayCartToaster(response) {
        const toast = $("#basket-toaster");

        response.json().then(counter => {
            $("#basket-counter").innerText = counter;
            toast.style.display = "";
            setTimeout(() => {
                toast.style.display = "none";
            }, 2000);
        });
    }
    inCart(evt) {
        const target = evt.target;
        const exist = $("#cart_display_exist");
        const empty = $("#cart_display_none");
        const productId = target.dataset.no;
        const quantity = target.closest(".prd_to_basket").querySelector(".prd_account label input").value;

        if(empty.style.display === "") {
            exist.style.display = "";
            empty.style.display = "none";
        }
        $("#basket-toaster .prd_name").innerText = evt.target.closest(".prds_lst_info").querySelector(".prd_tlt").innerText;
        target.closest(".prd_to_basket").querySelector(".prd_account label input").value = 1;

        postData({
            url: "/api/cart/"+productId,
            body: {
                "productId": productId,
                "quantity": quantity
            },
            callback: this.displayCartToaster.bind(this)
        });
    }
    productsClickHandler(evt) {
        const targetClassName = evt.target.classList;
        if(!targetClassName.contains("up") && !targetClassName.contains("down") && !targetClassName.contains("cart")) {
            return;
        }
        if(targetClassName.contains("up") || targetClassName.contains("down")) {
            this.accountChange(evt);
            return;
        }
        if(targetClassName.contains("cart")) {
            this.inCart(evt);
            return;
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
   new Cart();
});