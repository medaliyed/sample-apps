/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  15-Jun-20 3:29:50 PM                     */
/*==============================================================*/


drop table if exists Countries;

drop table if exists Favorites;

drop table if exists Games;

drop table if exists Players;

drop table if exists Types;

drop table if exists association4;

/*==============================================================*/
/* Table : Countries                                            */
/*==============================================================*/
create table Countries
(
   idCountry            int not null,
   nameCountry          varchar(254),
   primary key (idCountry)
);

/*==============================================================*/
/* Table : Favorites                                            */
/*==============================================================*/
create table Favorites
(
   idFav                int not null,
   idPlayer             int not null,
   idGame               int not null,
   primary key (idFav)
);

/*==============================================================*/
/* Table : Games                                                */
/*==============================================================*/
create table Games
(
   idGame               int not null,
   idType               int not null,
   gameName             varchar(254),
   primary key (idGame)
);

/*==============================================================*/
/* Table : Players                                              */
/*==============================================================*/
create table Players
(
   idPlayer             int not null,
   idCountry            int not null,
   playerName           varchar(254),
   primary key (idPlayer)
);

/*==============================================================*/
/* Table : Types                                                */
/*==============================================================*/
create table Types
(
   idType               int not null,
   typeName             varchar(254),
   primary key (idType)
);

/*==============================================================*/
/* Table : association4                                         */
/*==============================================================*/
create table association4
(
   idGame               int not null,
   idCountry            int not null,
   primary key (idGame, idCountry)
);

alter table Favorites add constraint FK_association6 foreign key (idPlayer)
      references Players (idPlayer) on delete cascade on update cascade;

alter table Favorites add constraint FK_association7 foreign key (idGame)
      references Games (idGame) on delete cascade on update cascade;

alter table Games add constraint FK_association8 foreign key (idType)
      references Types (idType) on delete cascade on update cascade;

alter table Players add constraint FK_association5 foreign key (idCountry)
      references Countries (idCountry) on delete cascade on update cascade;

alter table association4 add constraint FK_association4 foreign key (idCountry)
      references Countries (idCountry) on delete cascade on update cascade;

alter table association4 add constraint FK_association4 foreign key (idGame)
      references Games (idGame) on delete cascade on update cascade;

