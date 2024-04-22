function test_post() {
    fetch('/test1', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({title: 'test1', price: 1000})
    })
        .then(response => response.json())
        .then(data => console.log(data));
}

function test_get() {
    fetch('/test2?title=test2&price=2000', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => console.log(data));
}

function test_delete(item) {
    var id = item.getAttribute('data-id'); // Get the id from the data-id attribute
    fetch('/deleteItem/' + id, {
        method: 'DELETE',
        credentials: 'same-origin' // Include cookies in the request
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => console.log(data))
        .catch(error => console.error('There has been a problem with your fetch operation:', error));
}

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.btn')[0].addEventListener('click', test_post);
    document.querySelectorAll('.btn')[1].addEventListener('click', test_get);
    document.querySelectorAll('.deleteItem').forEach(function(item) {
        item.addEventListener('click', function() {
            test_delete(item);
        });
    });
});