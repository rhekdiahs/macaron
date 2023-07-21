let curImageCount = 0;
$(function(){
    const upload = document.getElementById("upload");
    const imagePreviews = document.getElementById("imagePreviews");
	const uploadUL = document.getElementById("forUpload-ul");
	let maxImageCount = 4;

	
	upload.addEventListener("change", function(){
		let files = this.files;
		
		curImageCount += files.length;
		
		if(curImageCount > maxImageCount){
			curImageCount = uploadUL.childElementCount;
			alert(maxImageCount + '개까지만 등록 가능합니다.');
			return;
		}
		if(uploadUL.childElementCount > maxImageCount-1){
			alert(maxImageCount + '개까지만 등록 가능합니다.');
			return;
		}
		for(let i = 0; i < files.length; i++){
			let file = files[i];
			let reader = new FileReader(); 
			reader.readAsDataURL(file);
			
			reader.onload = function(){
				uploadUL.style.display = "";
				let imageSrc = reader.result;
				
				let imgLi = document.createElement("li");
				uploadUL.appendChild(imgLi);
				
				let selectedImg = document.createElement("img");
				selectedImg.src = imageSrc;
				selectedImg.className = "preview-img";
				imgLi.appendChild(selectedImg);
				
				let delBtn = document.createElement("input");
				delBtn.type = "button";
				delBtn.className = "preview-del-btn";
				delBtn.value = "X";
				delBtn.dataset.fileIndex = i;
				imgLi.appendChild(delBtn);
			}
		}
	});

	imagePreviews.addEventListener("click", function (event) {
		if (event.target.classList.contains("preview-del-btn")) {
			const imagePreview = event.target.closest("#forUpload-ul > li");
			imagePreview.remove();
			curImgageCount--;
		}
	});
});