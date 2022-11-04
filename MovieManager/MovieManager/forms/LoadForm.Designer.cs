namespace MovieManager.forms
{
    partial class LoadForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoadForm));
            this.txtYear = new System.Windows.Forms.TextBox();
            this.boxMovie = new System.Windows.Forms.GroupBox();
            this.chkImage = new System.Windows.Forms.CheckBox();
            this.btnStart = new System.Windows.Forms.Button();
            this.txtHowMany = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.txtLog = new System.Windows.Forms.TextBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnDaily = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.btnWeekly = new System.Windows.Forms.Button();
            this.chkImageVisible = new System.Windows.Forms.CheckBox();
            this.boxMovie.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // txtYear
            // 
            this.txtYear.Location = new System.Drawing.Point(181, 26);
            this.txtYear.Name = "txtYear";
            this.txtYear.Size = new System.Drawing.Size(254, 27);
            this.txtYear.TabIndex = 0;
            // 
            // boxMovie
            // 
            this.boxMovie.Controls.Add(this.chkImageVisible);
            this.boxMovie.Controls.Add(this.chkImage);
            this.boxMovie.Controls.Add(this.btnStart);
            this.boxMovie.Controls.Add(this.txtHowMany);
            this.boxMovie.Controls.Add(this.label2);
            this.boxMovie.Controls.Add(this.label1);
            this.boxMovie.Controls.Add(this.txtYear);
            this.boxMovie.Location = new System.Drawing.Point(26, 44);
            this.boxMovie.Name = "boxMovie";
            this.boxMovie.Size = new System.Drawing.Size(636, 164);
            this.boxMovie.TabIndex = 1;
            this.boxMovie.TabStop = false;
            this.boxMovie.Text = "영화";
            // 
            // chkImage
            // 
            this.chkImage.AutoSize = true;
            this.chkImage.Checked = true;
            this.chkImage.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkImage.Location = new System.Drawing.Point(506, 92);
            this.chkImage.Name = "chkImage";
            this.chkImage.Size = new System.Drawing.Size(111, 24);
            this.chkImage.TabIndex = 5;
            this.chkImage.Text = "이미지 로드";
            this.chkImage.UseVisualStyleBackColor = true;
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(493, 35);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(124, 51);
            this.btnStart.TabIndex = 3;
            this.btnStart.Text = "로드 시작";
            this.btnStart.UseVisualStyleBackColor = true;
            // 
            // txtHowMany
            // 
            this.txtHowMany.Location = new System.Drawing.Point(181, 59);
            this.txtHowMany.Name = "txtHowMany";
            this.txtHowMany.Size = new System.Drawing.Size(254, 27);
            this.txtHowMany.TabIndex = 4;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(40, 62);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(95, 20);
            this.label2.TabIndex = 3;
            this.label2.Text = "How many : ";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(40, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(135, 20);
            this.label1.TabIndex = 2;
            this.label1.Text = "Year from (YYYY) : ";
            // 
            // txtLog
            // 
            this.txtLog.Enabled = false;
            this.txtLog.Location = new System.Drawing.Point(26, 408);
            this.txtLog.Multiline = true;
            this.txtLog.Name = "txtLog";
            this.txtLog.Size = new System.Drawing.Size(636, 295);
            this.txtLog.TabIndex = 2;
            this.txtLog.Text = resources.GetString("txtLog.Text");
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btnDaily);
            this.groupBox2.Location = new System.Drawing.Point(26, 214);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(311, 164);
            this.groupBox2.TabIndex = 5;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "일간 순위";
            // 
            // btnDaily
            // 
            this.btnDaily.Location = new System.Drawing.Point(181, 107);
            this.btnDaily.Name = "btnDaily";
            this.btnDaily.Size = new System.Drawing.Size(124, 51);
            this.btnDaily.TabIndex = 3;
            this.btnDaily.Text = "로드 시작";
            this.btnDaily.UseVisualStyleBackColor = true;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.btnWeekly);
            this.groupBox3.Location = new System.Drawing.Point(351, 214);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(311, 164);
            this.groupBox3.TabIndex = 6;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "주간 순위";
            // 
            // btnWeekly
            // 
            this.btnWeekly.Location = new System.Drawing.Point(181, 107);
            this.btnWeekly.Name = "btnWeekly";
            this.btnWeekly.Size = new System.Drawing.Size(124, 51);
            this.btnWeekly.TabIndex = 3;
            this.btnWeekly.Text = "로드 시작";
            this.btnWeekly.UseVisualStyleBackColor = true;
            // 
            // chkImageVisible
            // 
            this.chkImageVisible.AutoSize = true;
            this.chkImageVisible.Location = new System.Drawing.Point(441, 122);
            this.chkImageVisible.Name = "chkImageVisible";
            this.chkImageVisible.Size = new System.Drawing.Size(176, 24);
            this.chkImageVisible.TabIndex = 6;
            this.chkImageVisible.Text = "이미지 로드 구경하기";
            this.chkImageVisible.UseVisualStyleBackColor = true;
            // 
            // LoadForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(707, 729);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.txtLog);
            this.Controls.Add(this.boxMovie);
            this.Name = "LoadForm";
            this.Text = "LoadForm";
            this.Load += new System.EventHandler(this.LoadForm_Load);
            this.boxMovie.ResumeLayout(false);
            this.boxMovie.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private TextBox txtYear;
        private GroupBox boxMovie;
        private TextBox txtHowMany;
        private Label label2;
        private Label label1;
        private TextBox txtLog;
        private Button btnStart;
        private GroupBox groupBox2;
        private Button btnDaily;
        private GroupBox groupBox3;
        private Button btnWeekly;
        private CheckBox chkImage;
        private CheckBox chkImageVisible;
    }
}