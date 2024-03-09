function waitingTimeExpired(time) {
    return setTimeout(() => {
        notice.hideLoading();
        Swal.fire({
            title: "",
            text: "The waiting time has expired",
            icon: "error",
        });
    },time)
}

function showNotice(text) {
    notice.showLoading({
        type: 'dots',  // default：'line'
        color: '#ffffff',
        backgroundColor: 'rgba(0,0,0,.7)',
        title: text,
        fontSize: 20,
    });
}

function stopTimer(timer) {
    if (timer) {
        clearTimeout(timer);
    }
}