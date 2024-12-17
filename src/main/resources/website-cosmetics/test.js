const imageUrl = 'https://tantienvet.com/wp-content/uploads/2022/07/chan-nuoi-ga-con-2-1.jpg';  // Sửa URL đúng
// const imageUrl = '3811a2d8-f239-4be9-977d-735a0e8ab738.jpg';  // Sửa URL đúng
const apiUrl = `http://localhost:8081/api/images?imageName=${imageUrl}`;

fetch(apiUrl)
    .then(response => {
        if (response.ok) {
            return response.blob();  // Chuyển đổi response thành Blob (ảnh)
        }
        throw new Error('Failed to fetch image');
    })
    .then(imageBlob => {
        // Tạo URL cho blob và hiển thị ảnh
        const imageObjectURL = URL.createObjectURL(imageBlob);
        console.log(imageObjectURL);

    })
    .catch(error => {
        console.error('Error fetching image:', error);
    });
