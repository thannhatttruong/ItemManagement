USE [master]
GO
/****** Object:  Database [SUPPLIER_ITEM]    Script Date: 11/19/2019 9:15:20 AM ******/
CREATE DATABASE [SUPPLIER_ITEM]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SUPPLIER_ITEM', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\SUPPLIER_ITEM.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SUPPLIER_ITEM_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\SUPPLIER_ITEM_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [SUPPLIER_ITEM] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SUPPLIER_ITEM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ARITHABORT OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET RECOVERY FULL 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET  MULTI_USER 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SUPPLIER_ITEM] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SUPPLIER_ITEM] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SUPPLIER_ITEM', N'ON'
GO
ALTER DATABASE [SUPPLIER_ITEM] SET QUERY_STORE = OFF
GO
USE [SUPPLIER_ITEM]
GO
/****** Object:  Table [dbo].[ITEMS]    Script Date: 11/19/2019 9:15:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ITEMS](
	[itemCode] [nvarchar](5) NOT NULL,
	[itemName] [nvarchar](50) NULL,
	[supCode] [nchar](5) NULL,
	[unit] [nvarchar](10) NULL,
	[price] [int] NULL,
	[supplying] [bit] NULL,
 CONSTRAINT [PK_ITEMS] PRIMARY KEY CLUSTERED 
(
	[itemCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SUPPLIERS]    Script Date: 11/19/2019 9:15:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SUPPLIERS](
	[supCode] [nchar](5) NOT NULL,
	[supName] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[colloborating] [bit] NULL,
 CONSTRAINT [PK_SUPPLIERS] PRIMARY KEY CLUSTERED 
(
	[supCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ITEMS] ([itemCode], [itemName], [supCode], [unit], [price], [supplying]) VALUES (N'123', N'1234', N'nt   ', N'1321', 987, 1)
INSERT [dbo].[ITEMS] ([itemCode], [itemName], [supCode], [unit], [price], [supplying]) VALUES (N'258', N'Phone', N'Viet ', N'12', 2321, 1)
INSERT [dbo].[ITEMS] ([itemCode], [itemName], [supCode], [unit], [price], [supplying]) VALUES (N'T003', N'Tivi', N'nt   ', N'23', 3000000, 0)
INSERT [dbo].[SUPPLIERS] ([supCode], [supName], [address], [colloborating]) VALUES (N'nt   ', N'Than Nhat Truong', N'Nhon Trach', 1)
INSERT [dbo].[SUPPLIERS] ([supCode], [supName], [address], [colloborating]) VALUES (N'TV   ', N'Than Van', N'Ho Nai', 0)
INSERT [dbo].[SUPPLIERS] ([supCode], [supName], [address], [colloborating]) VALUES (N'Viet ', N'Than Cong Viet', N'Thiet Nham', 1)
ALTER TABLE [dbo].[ITEMS]  WITH CHECK ADD  CONSTRAINT [FK_ITEMS_SUPPLIERS] FOREIGN KEY([supCode])
REFERENCES [dbo].[SUPPLIERS] ([supCode])
GO
ALTER TABLE [dbo].[ITEMS] CHECK CONSTRAINT [FK_ITEMS_SUPPLIERS]
GO
USE [master]
GO
ALTER DATABASE [SUPPLIER_ITEM] SET  READ_WRITE 
GO
