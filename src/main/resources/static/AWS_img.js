        window.onload = function() {
            autoSave();
            autoLoad();
        };
        function autoSave() {
            const imageLinks = [
                "https://fullstack-nkh.s3.ap-northeast-2.amazonaws.com/imgs/hongkong.jpg",
                "https://fullstack-nkh.s3.ap-northeast-2.amazonaws.com/imgs/images.jpg",
            ];

            localStorage.setItem("autoBooks", JSON.stringify(imageLinks));
            document.getElementById("status");
            console.log("이미지저장");
        }

        function autoLoad() {
            const savedData = localStorage.getItem("autoBooks");

            if (savedData) {
                const imageLinks = JSON.parse(savedData);
                const displayArea = document.getElementById("display-area");
                imageLinks.forEach((link, index) => {
                    const img = document.createElement("img");
                    img.src = link;
                    displayArea.appendChild(img);
                });
                
                console.log("데이터 출력 완료");
            }
        }