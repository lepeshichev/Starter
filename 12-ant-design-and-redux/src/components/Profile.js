const user = {
    name: 'Admin',
    imageUrl: 'http://cdn.onlinewebfonts.com/svg/img_235552.png',
    imageSize: 35,
}
export const Profile = () => {
    return (
        <>
            <h3>{user.name} <img
                className="avatar"
                src={user.imageUrl}
                alt={'Фото ' + user.name}
                style={{
                    width: user.imageSize,
                    height: user.imageSize
                }}
            />
            </h3>
        </>
    );
}