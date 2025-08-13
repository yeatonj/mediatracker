-- user
CREATE TABLE IF NOT EXISTS user_main (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    role VARCHAR DEFAULT 'user',
    birthday TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT now()
);

-- books
CREATE TABLE IF NOT EXISTS book (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR NOT NULL,
    author VARCHAR NOT NULL,
    series VARCHAR,
    pages INTEGER,
    description VARCHAR NOT NULL,
    published TIMESTAMPTZ,
    cover_img_loc VARCHAR
);

-- tags
CREATE TABLE IF NOT EXISTS tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tag VARCHAR UNIQUE NOT NULL
);

-- genres
CREATE TABLE IF NOT EXISTS genre (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    genre VARCHAR UNIQUE NOT NULL
);

-- book_wishlist
CREATE TABLE IF NOT EXISTS book_wishlist (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES user_main,
    book_id UUID NOT NULL REFERENCES book,
    rank INTEGER DEFAULT NULL
);

--book_tags
CREATE TABLE IF NOT EXISTS book_tag (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id UUID NOT NULL REFERENCES book,
    tag_id UUID NOT NULL REFERENCES tag
);

--book_genres
CREATE TABLE IF NOT EXISTS book_genre(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id UUID NOT NULL REFERENCES book,
    genre_id UUID NOT NULL REFERENCES genre
);

--books_read
CREATE TABLE IF NOT EXISTS books_read(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES user_main,
    book_id UUID NOT NULL REFERENCES book,
    progress INTEGER NOT NULL DEFAULT 0,
    rating INTEGER DEFAULT NULL,
    review VARCHAR,
    completed BOOLEAN DEFAULT FALSE,
    completed_date TIMESTAMPTZ
);

--user_tags
CREATE TABLE IF NOT EXISTS user_tag(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_read_id UUID REFERENCES books_read,
    tag_id UUID REFERENCES tag
);